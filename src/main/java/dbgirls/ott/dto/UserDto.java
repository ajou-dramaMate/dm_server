package dbgirls.ott.dto;

import dbgirls.ott.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;

    private String email;

    private String refreshToken;

    public User toEntity(String name, String email, String refreshToken) {
        return User.builder()
                .name(name)
                .email(email)
                .refreshToken(refreshToken)
                .build();
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
