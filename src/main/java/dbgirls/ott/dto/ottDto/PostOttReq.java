package dbgirls.ott.dto.ottDto;

import dbgirls.ott.domain.*;
import dbgirls.ott.dto.dramaDto.PostReviewReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostOttReq {
    private String ott_image;

    private OttType name;

    private Integer price;
    public Ott toEntity(PostOttReq postOttReq) {
        return Ott.builder()
                .name(postOttReq.name)
                .price(postOttReq.price)
                .build();
    }
}
