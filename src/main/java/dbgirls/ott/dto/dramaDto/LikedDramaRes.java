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
public class LikedDramaRes {

    private Long dramaId;

    private String title;

//    private byte[] image;

    public static LikedDramaRes fromEntity(Long dramaId, String title) {
        return LikedDramaRes.builder()
                .dramaId(dramaId)
                .title(title)
//                .image(drama.getImage())
                .build();
    }
}
