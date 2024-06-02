package dbgirls.ott.controller;

import dbgirls.ott.dto.loginDto.JwtDto;
import dbgirls.ott.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/login")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @PostMapping("")
//    public ResponseEntity<?> signUp(@RequestBody @Validated SignUpDto signUpDto) {
//        return ResponseEntity.ok().body(userService.signUpService(signUpDto));
//    }
    @GetMapping("/test-login")
    public JwtDto loginServiceForTestUser() {
    return userService.loginForTestUser();
}

    @GetMapping("")
    public String testapi() {
        return "successful code";
    }

}
