package dbgirls.ott.dto.dramaDto;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.domain.Genre;
import dbgirls.ott.domain.User;
import lombok.*;

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

    private Genre genre;

//    private byte[] image;

    public Drama toEntity(PostDramaReq postDramaReq) {
        return Drama.builder()
                .title(postDramaReq.title)
                .member(postDramaReq.member)
                .summary(postDramaReq.summary)
                .year(postDramaReq.year)
                .age(postDramaReq.age)
                .information(postDramaReq.information)
                .genre(postDramaReq.genre)
//                .image(postDramaReq.image)
                .build();
    }

}

