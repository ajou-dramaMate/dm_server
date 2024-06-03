package dbgirls.ott.dto.dramaDto;

import dbgirls.ott.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostReviewReq {
    private Long dramaId;
    private String contents;
    private Integer star;

    public Review toEntity(PostReviewReq postReviewReq, User user, Drama drama) {
        return Review.builder()
                .contents(postReviewReq.getContents())
                .star(postReviewReq.getStar())
                .user(user)
                .drama(drama)
                .build();
    }
}
