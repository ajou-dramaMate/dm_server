package dbgirls.ott.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbgirls.ott.domain.User;
import dbgirls.ott.dto.UserDto;
import dbgirls.ott.dto.loginDto.JwtDto;
import dbgirls.ott.dto.loginDto.OAuthResponseDto;
import dbgirls.ott.dto.loginDto.UserDataDto;
import dbgirls.ott.jwt.JwtTokenUtil;
import dbgirls.ott.repository.UserRepository;
import io.jsonwebtoken.impl.Base64UrlCodec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final String kakaoTokenUrl = "https://kauth.kakao.com/oauth/token";

    @Value("${oauth2.client.kakao.client-id}")
    private String clientId;

    @Value("${oauth2.client.kakao.client-secret}")
    private String clientSecret;

    @Value("${oauth2.client.kakao.redirect-uri}")
    private String redirectUrl;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UserRepository userRepository;

    public String loadToLogin() {
        String loginUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + clientId + "&redirect_uri=" + redirectUrl
                + "&response_type=code";
        return loginUrl;
    }

    // 서비스의 자체 회원가입,로그인을 진행하는 메서드
    public JwtDto loginToService(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("code", accessToken);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUrl);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<OAuthResponseDto> responseEntity = restTemplate.postForEntity(kakaoTokenUrl, requestEntity, OAuthResponseDto.class);
        System.out.println("responseEntity : " + responseEntity.getBody().getAccess_token() + responseEntity.getBody().getId_token());

        Optional<UserDataDto> userDataDto = decodeToken(responseEntity.getBody().getId_token().split("\\.")[1]);


        Optional<User> user = userRepository.findByEmail(userDataDto.get().getEmail());
        JwtDto jwtDto = JwtTokenUtil.createToken(userDataDto.get().getEmail(), secretKey);
        UserDto userDto = new UserDto();

        if (user.isEmpty()) { // 회원가입
            User signUpUser = userDto.toEntity(userDataDto.get().getNickname(), userDataDto.get().getEmail(), jwtDto.getRefreshToken());
            userRepository.save(signUpUser);
        }
        else { // 로그인
            User existUser = user.get();
            existUser.setRefreshToken(jwtDto.getRefreshToken());
            userRepository.save(existUser);
        }

        return jwtDto;
    }

    public JwtDto issueRefreshToken(String refreshToken) {
        String email = JwtTokenUtil.getUserEmail(refreshToken, secretKey);
        Optional<User> user = userRepository.findByEmail(email);
        User refreshedUser = user.get();

        JwtDto jwtDto = JwtTokenUtil.createToken(email, secretKey);
        refreshedUser.setRefreshToken(jwtDto.getRefreshToken());
        userRepository.save(refreshedUser);

        return jwtDto;
    }

    public JwtDto loginForTestUser() {
        Optional<User> user = userRepository.findByEmail("testuser@email");
        JwtDto jwtDto = JwtTokenUtil.createToken("testuser@email", secretKey);

        if(user.isEmpty()) {
            User testUser = new User("testuser@email", "testuser", jwtDto.getRefreshToken());
            userRepository.save(testUser);
        }
        else {
            User testUser = user.get();
            testUser.setRefreshToken(jwtDto.getRefreshToken());
            userRepository.save(testUser);
        }

        return jwtDto;
    }

    public String getUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String)authentication.getCredentials();
        return email;
    }

    public Optional<UserDataDto> decodeToken(String jwtToken) {
        byte[] decode = new Base64UrlCodec().decode(jwtToken);
        String decode_data = new String(decode, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            UserDataDto userDataDto = objectMapper.readValue(decode_data, UserDataDto.class);
            return Optional.ofNullable(userDataDto);
        }
        catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}