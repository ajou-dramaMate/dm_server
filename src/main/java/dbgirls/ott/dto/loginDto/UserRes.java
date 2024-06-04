package dbgirls.ott.dto.loginDto;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.domain.User;
import dbgirls.ott.dto.dramaDto.DramaRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    private String email;
    private String name;

    public static UserRes fromEntity(User user) {
        return UserRes.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
