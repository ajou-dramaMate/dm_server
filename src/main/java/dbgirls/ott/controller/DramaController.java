package dbgirls.ott.controller;

import dbgirls.ott.dto.dramaDto.PostDramaReq;
import dbgirls.ott.service.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/drama")
@RestController
@RequiredArgsConstructor
public class DramaController {
    private final DramaService dramaService;

    @GetMapping("")
    public ResponseEntity<?> getDrama() {
        return ResponseEntity.ok().body(dramaService.getDramaInfo());
    }

    @GetMapping("/{drama_id}")
    public ResponseEntity<?> getDetailDrama(@PathVariable("drama_id") Long dramaId) {
        return ResponseEntity.ok().body(dramaService.getDetailDramaInfo(dramaId));
    }

    @PostMapping("")
    public ResponseEntity<?> postDrama(@RequestBody @Validated PostDramaReq postDramaReq) {
        return ResponseEntity.ok().body(dramaService.postDramaInfo(postDramaReq));
    }

    @GetMapping("/like")
    public ResponseEntity<?> getLikedDrama() {
        return ResponseEntity.ok().body(dramaService.getLikedDramaList());
    }

    @PostMapping("/like/{drama_id}")
    public ResponseEntity<?> postLikedDrama(@PathVariable("drama_id") Long dramaId) {
        return ResponseEntity.ok().body(dramaService.postLikedDrama(dramaId));
    }
}
