package dbgirls.ott.dto.ottDto;

import dbgirls.ott.domain.Ott;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OttReq {

    private Long drama1;

    private Long drama2;

    private Long drama3;
}
