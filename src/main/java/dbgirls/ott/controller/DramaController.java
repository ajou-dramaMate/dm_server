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

    @PostMapping("")
    public ResponseEntity<?> postDrama(@RequestBody @Validated PostDramaReq postDramaReq) {
        return ResponseEntity.ok().body(dramaService.postDramaInfo(postDramaReq));
    }


}
