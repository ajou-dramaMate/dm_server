package dbgirls.ott.dto.dramaDto;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DramaDetailRes {
    private String title;

    private Integer star;

    private String member;

    private Integer year;

    private Integer age;

    private Genre genre;

    private String information;

    //    private byte[] image;

    private String summary;

    //review

    public static DramaDetailRes fromEntity(Drama drama) {
        return DramaDetailRes.builder()
                .title(drama.getTitle())
                .star(5)
                .member(drama.getMember())
                .year(drama.getYear())
                .age(drama.getAge())
                .genre(drama.getGenre())
                .information(drama.getInformation())
//                .image(drama.getImage())
                .summary(drama.getSummary())
//                .review
                .build();
    }
}