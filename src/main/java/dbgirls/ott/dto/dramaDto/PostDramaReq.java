package dbgirls.ott.dto.dramaDto;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.domain.Genre;
import dbgirls.ott.domain.OttType;
import dbgirls.ott.domain.User;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDramaReq {

    private String title;

    private String member;

    private String summary;

    private Integer year;

    private Integer age;

    private String information;

    private List<Genre> genre;

    private String image;

    private List<OttType> ott;

    public Drama toEntity(PostDramaReq postDramaReq) {
        return Drama.builder()
                .title(postDramaReq.title)
                .member(postDramaReq.member)
                .summary(postDramaReq.summary)
                .year(postDramaReq.year)
                .age(postDramaReq.age)
                .information(postDramaReq.information)
                .genre(postDramaReq.genre)
                .build();
    }

}

