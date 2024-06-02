package dbgirls.ott.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbgirls.ott.domain.User;
import dbgirls.ott.dto.UserDto;
import dbgirls.ott.dto.loginDto.JwtDto;
import dbgirls.ott.dto.loginDto.UserDataDto;
import dbgirls.ott.jwt.JwtTokenUtil;
import dbgirls.ott.repository.UserRepository;
import io.jsonwebtoken.impl.Base64UrlCodec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final String kakaoTokenUrl = "https://oauth2.googleapis.com/token";

    @Value("${oauth2.client.kakao.client-id}")
    private String clientId;

    @Value("${oauth2.client.kakao.client-secret}")
    private String clientSecret;

    @Value("${oauth2.client.kakao.redirect-uri}")
    private String redirectUrl;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UserRepository userRepository;

    // 서비스의 자체 회원가입,로그인을 진행하는 메서드
    public JwtDto loginToService(String id_token, String social) {

        UserDto userDto = decodeToken(id_token.split("\\.")[1], social);
        Optional<User> user = userRepository.findByEmail(userDto.getEmail());

        JwtDto jwtDto = JwtTokenUtil.createToken(userDto.getEmail(), secretKey);

        if (user.isEmpty()) { // 회원가입
            User signUpUser = userDto.toEntity(userDto.getName(),userDto.getEmail(), jwtDto.getRefreshToken());
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
        User testUser = user.get();
        testUser.setRefreshToken(jwtDto.getRefreshToken());
        userRepository.save(testUser);

        return jwtDto;
    }

    public UserDto decodeToken(String jwtToken, String social) {
        byte[] decode = new Base64UrlCodec().decode(jwtToken);
        String decode_data = new String(decode, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            UserDataDto userDataDto = objectMapper.readValue(decode_data, UserDataDto.class);
            UserDto userDto = new UserDto();
            userDto.setEmail(userDataDto.getEmail());
            userDto.setName(userDataDto.getNickname());
            return userDto;
        }
        catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}