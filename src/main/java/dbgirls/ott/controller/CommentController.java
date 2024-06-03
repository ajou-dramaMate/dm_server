package dbgirls.ott.controller;

import dbgirls.ott.dto.communityDto.PostCommentReq;
import dbgirls.ott.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> postComment(@RequestBody @Validated PostCommentReq postCommentReq) {
        return ResponseEntity.ok().body(commentService.postCommentInfo(postCommentReq));
    }

}
