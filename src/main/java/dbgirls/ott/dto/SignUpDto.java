package dbgirls.ott.dto;

import dbgirls.ott.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpDto {
    private String id;

    private String pw;

    private String nickname;

    public Member toEntity (){
        return Member.builder()
                .id(id)
                .pw(pw)
                .nickname(nickname)
                .build();
    }
}
