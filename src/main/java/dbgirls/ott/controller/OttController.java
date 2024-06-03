package dbgirls.ott.controller;

import dbgirls.ott.dto.dramaDto.PostDramaReq;
import dbgirls.ott.dto.ottDto.PostOttReq;
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
//    @GetMapping("/{drama_id1}/{drama_id2}/{drama_id3}")
//    public ResponseEntity<?> getDetailDrama(@PathVariable("drama_id1") Long dramaId1, ) {
//        return ResponseEntity.ok().body(dramaService.getDetailDramaInfo(dramaId));
//    }

    @PostMapping("")
    public ResponseEntity<?> postDrama() {
        return ResponseEntity.ok().body(ottService.postOttInfo());
    }
}
