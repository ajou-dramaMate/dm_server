package dbgirls.ott.dto.communityDto;

import dbgirls.ott.domain.Community;
import dbgirls.ott.domain.OttType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityRes {
    private Long communityId;
    private String title;
    private String name; // nickname?
    private LocalDate date;
    private boolean spoiler;
    private OttType ott;
    private Integer totalRecruit;
    private Integer currentRecruit;
    private boolean recruitStatus;

    public static CommunityRes fromEntity(Community community) {
        return CommunityRes.builder()
                .communityId(community.getId())
                .title(community.getTitle())
                .name(community.getUser().getName())
                .date(community.getDate())
                .spoiler(community.isSpoiler())
                .ott(community.getOtt())
                .totalRecruit(community.getTotalRecruit())
                .currentRecruit(community.getCurrentRecruit())
                .recruitStatus(community.isRecruitStatus())
                .build();
    }
}
