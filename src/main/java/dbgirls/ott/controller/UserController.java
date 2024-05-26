package dbgirls.ott.controller;

import dbgirls.ott.dto.SignUpDto;
import dbgirls.ott.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> signUp(@RequestBody @Validated SignUpDto signUpDto) {
        return ResponseEntity.ok().body(userService.signUpService(signUpDto));
    }

    @GetMapping("")
    public String testapi() {
        return "successful code";
    }

}
