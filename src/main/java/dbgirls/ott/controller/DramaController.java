package dbgirls.ott.controller;

import dbgirls.ott.dto.dramaDto.DramaDetailRes;
import dbgirls.ott.dto.dramaDto.DramaRes;
import dbgirls.ott.dto.dramaDto.PostDramaReq;
import dbgirls.ott.service.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/drama")
@RestController
@RequiredArgsConstructor
public class DramaController {
    private final DramaService dramaService;

    @GetMapping("")
    public ResponseEntity<?> getDrama(@RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        Slice<DramaRes> dramaResSlice = dramaService.getDramaInfo(page);
        return new ResponseEntity<>(dramaResSlice, HttpStatus.OK);
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
