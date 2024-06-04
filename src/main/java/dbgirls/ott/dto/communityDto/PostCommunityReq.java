package dbgirls.ott.dto.communityDto;

import dbgirls.ott.domain.Community;
import dbgirls.ott.domain.OttType;
import dbgirls.ott.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCommunityReq {

    private String title;

    private boolean spoiler;

    private OttType ott;

    private Integer totalRecruit;

    private String contents;

    public Community toEntity(PostCommunityReq postCommunityReq, User user) {
        return Community.builder()
                .title(postCommunityReq.title)
                .spoiler(postCommunityReq.spoiler)
                .ott(postCommunityReq.ott)
                .totalRecruit(postCommunityReq.totalRecruit)
                .contents(postCommunityReq.contents)
                .user(user)
                .build();
    }
}
