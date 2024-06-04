package dbgirls.ott.controller;

import dbgirls.ott.dto.ottDto.OttReq;
import dbgirls.ott.service.OttService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/ott")
@RestController
@RequiredArgsConstructor
public class OttController {

    private final OttService ottService;

    @GetMapping("")
    public ResponseEntity<?> getDrama(@RequestBody @Validated OttReq ottReq) {
        return ResponseEntity.ok().body(ottService.getOttRecommendResult(ottReq));
    }

    @PostMapping("")
    public ResponseEntity<?> postDrama() {
        return ResponseEntity.ok().body(ottService.postOttInfo());
    }
}
