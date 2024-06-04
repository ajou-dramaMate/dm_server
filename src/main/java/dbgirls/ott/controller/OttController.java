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

    @GetMapping("/{drama1}/{drama2}/{drama3}")
    public ResponseEntity<?> getDrama(@PathVariable("drama1") Long dramaId1,
                                      @PathVariable("drama2") Long dramaId2,
                                      @PathVariable("drama3") Long dramaId3) {
        return ResponseEntity.ok().body(ottService.getOttRecommendResult(dramaId1, dramaId2, dramaId3));
    }

    @PostMapping("")
    public ResponseEntity<?> postDrama() {
        return ResponseEntity.ok().body(ottService.postOttInfo());
    }
}
