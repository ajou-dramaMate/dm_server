package dbgirls.ott.dto.dramaDto;

import dbgirls.ott.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRes {
    private String contents;

    private String name;

    private LocalDate date;

    private Integer star;

    public static ReviewRes fromEntity(Review review) {
        return ReviewRes.builder()
                .contents(review.getContents())
                .name(review.getUser().getName())
                .date(review.getDate())
                .star(review.getStar())
                .build();
    }
}
