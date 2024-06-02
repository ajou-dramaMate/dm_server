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
    private String title;

//    private byte[] image;

    public static LikedDramaRes fromEntity(String title) {
        return LikedDramaRes.builder()
                .title(title)
//                .image(drama.getImage())
                .build();
    }
}
