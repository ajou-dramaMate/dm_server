package dbgirls.ott.dto.ottDto;

import dbgirls.ott.domain.Ott;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OttRes {
//    private byte[] ott_image;

    private Integer price;

    private List<String> dramas;

    public static OttRes fromEntity(Ott ott, List<String> dramas) {
        return OttRes.builder()
//                .ott_image(ott.getOtt_image())
                .price(ott.getPrice())
                .dramas(dramas)
                .build();
    }
}