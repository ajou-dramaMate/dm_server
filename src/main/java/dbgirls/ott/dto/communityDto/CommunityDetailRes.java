package dbgirls.ott.dto.communityDto;

import dbgirls.ott.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDetailRes {
    private String title;

    private String name;

    private LocalDate date;

    private String contents;

    private List<CommentRes> comments;

    public static CommunityDetailRes fromEntity(Community community) {
        return CommunityDetailRes.builder()
                .title(community.getTitle())
                .name(community.getUser().getName())
                .date(community.getDate())
                .contents(community.getContents())
                .comments(community.getComments().stream().map(CommentRes::fromEntity).toList())
                .build();
    }
}