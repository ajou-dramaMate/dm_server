package dbgirls.ott.dto.dramaDto;

import dbgirls.ott.domain.Drama;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DramaRes {
    private Long dramaId;

    private String title;

    private byte[] image;

    private boolean like;

    public static DramaRes fromEntity(Drama drama) {
        return DramaRes.builder()
                .dramaId(drama.getId())
                .title(drama.getTitle())
//                .image(drama.getImage())
                .like(drama.isLiked())
                .build();
    }
}
