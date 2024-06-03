package dbgirls.ott.dto.communityDto;

import dbgirls.ott.domain.Comment;
import dbgirls.ott.domain.Community;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRes {
    private String text;

    private String name;

    private LocalDate date;

    public static CommentRes fromEntity(Comment comment) {
        return CommentRes.builder()
                .text(comment.getText())
                .name(comment.getUser().getName())
                .date(comment.getDate())
                .build();
    }
}
