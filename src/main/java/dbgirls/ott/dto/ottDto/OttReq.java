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

    private String drama1;

    private String drama2;

    private String drama3;
}
