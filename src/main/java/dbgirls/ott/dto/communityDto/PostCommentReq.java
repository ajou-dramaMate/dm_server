package dbgirls.ott.dto.communityDto;

import dbgirls.ott.domain.Comment;
import dbgirls.ott.domain.Community;
import dbgirls.ott.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentReq {
    private Long communityId;
    private String text;

    public Comment toEntity(PostCommentReq postCommentReq, User user, Community community) {
        return Comment.builder()
                .text(postCommentReq.getText())
                .user(user)
                .community(community)
                .build();
    }
}
