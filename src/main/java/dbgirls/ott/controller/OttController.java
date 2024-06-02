package dbgirls.ott.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/ott")
@RestController
@RequiredArgsConstructor
public class OttController {
    @GetMapping("")
    public String testapi() {
        return "failure code";
    }
}
