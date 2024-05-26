package dbgirls.ott.service;

import dbgirls.ott.domain.Member;
import dbgirls.ott.dto.SignUpDto;
import dbgirls.ott.jwt.JwtTokenUtil;
import dbgirls.ott.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String signUpService (SignUpDto signUpDto) throws NullPointerException{
        String id = signUpDto.getId();
        Optional<Member> user = userRepository.findById(id);

        if (user.isEmpty()) { //회원가입 진행
            Member newUser = signUpDto.toEntity();
            userRepository.save(newUser);
            JwtTokenUtil.createToken(id, signUpDto.getPw());
            return "successful sign up to service";
        }
        else {
            JwtTokenUtil.createToken(id, signUpDto.getPw());
            return "already signed up to service";
        }

    }
}
