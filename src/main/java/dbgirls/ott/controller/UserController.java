package dbgirls.ott.controller;

import dbgirls.ott.dto.loginDto.JwtDto;
import dbgirls.ott.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/api/v1/login")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("")
    public RedirectView loadUrl () {    return new RedirectView(userService.loadToLogin());    }

    @GetMapping("/oauth2/code/kakao")
    public ResponseEntity<JwtDto> getAccessCode (@RequestParam("code") String accessCode) {
        System.out.println("access code " + accessCode);
        return ResponseEntity.ok().body(userService.loginToService(accessCode));
    }

    @GetMapping("/test-login")
    public JwtDto loginServiceForTestUser() {
    return userService.loginForTestUser();
}

//    @GetMapping("")
//    public String testapi() {
//        return "successful code";
//    }

}
