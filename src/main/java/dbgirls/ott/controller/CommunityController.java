package dbgirls.ott.controller;

import dbgirls.ott.dto.communityDto.PostCommunityReq;
import dbgirls.ott.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/community")
@RestController
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("")
    public ResponseEntity<?> getCommunity() {
        return ResponseEntity.ok().body(communityService.getCommunityInfo());
    }

    @GetMapping("/{community_id}")
    public ResponseEntity<?> getDetailDrama(@PathVariable("community_id") Long communityId) {
        return ResponseEntity.ok().body(communityService.getDetailCommunityInfo(communityId));
    }

    @PostMapping("")
    public ResponseEntity<?> postCommunity(@RequestBody @Validated PostCommunityReq postCommunityReq) {
        return ResponseEntity.ok().body(communityService.postCommunityInfo(postCommunityReq));
    }


}
