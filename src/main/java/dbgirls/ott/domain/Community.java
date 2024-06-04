package dbgirls.ott.domain;

import dbgirls.ott.dto.communityDto.CommentRes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "community")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean spoiler;

    private boolean recruitStatus;

    private Integer totalRecruit;

    private Integer currentRecruit;

    private OttType ott;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private final Set<Comment> comments = new HashSet<>();


    @Builder
    public Community(String title, boolean spoiler, OttType ott, Integer totalRecruit, String contents, User user) {
        this.title = title;
        this.spoiler = spoiler;
        this.ott = ott;
        this.totalRecruit = totalRecruit;
        this.contents = contents;
        this.user = user;
    }
}
