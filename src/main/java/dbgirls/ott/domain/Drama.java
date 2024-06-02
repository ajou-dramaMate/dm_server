package dbgirls.ott.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "drama")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drama_id")
    private Long dramaId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String member;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String information;

    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private boolean liked;

//    @Lob
//    @Column(nullable = false, length = 1000)
//    private byte[] image;

    @OneToMany(mappedBy = "drama", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private final Set<OttDramaRelation> ottDramaRelations = new HashSet<>();

    @OneToMany(mappedBy = "drama", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private final Set<Review> reviews = new HashSet<>();

    @Builder
    public Drama(String title, String member, String summary, Integer year, Integer age, String information, Genre genre) {
        this.title = title;
        this.member = member;
        this.summary = summary;
        this.year = year;
        this.age = age;
        this.information = information;
        this.genre = genre;
//        this.image = image;
    }
}
