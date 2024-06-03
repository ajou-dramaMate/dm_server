package dbgirls.ott.controller;

import dbgirls.ott.dto.communityDto.PostCommentReq;
import dbgirls.ott.dto.dramaDto.PostReviewReq;
import dbgirls.ott.service.CommentService;
import dbgirls.ott.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/review")
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<?> postReview(@RequestBody @Validated PostReviewReq postReviewReq) {
        return ResponseEntity.ok().body(reviewService.postReviewInfo(postReviewReq));
    }

}