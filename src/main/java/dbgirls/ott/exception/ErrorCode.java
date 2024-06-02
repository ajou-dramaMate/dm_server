package dbgirls.ott.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ACCESS_DENIED (403, "AUTH001", "접근 권한이 없습니다."),
    INVALID_TOKEN(401, "JWT001", "유효하지 않은 토큰입니다."),
    INVALID_SPEC(404, "SPEC001", "존재하지 않는 스펙입니다."),
    ;

    private int status;
    private final String code;
    private final String message;

}
