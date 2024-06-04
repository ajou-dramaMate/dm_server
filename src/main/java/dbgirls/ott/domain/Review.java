package dbgirls.ott.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false, length = 1000)
    private String contents;

    @Column(nullable = false)
    private Integer star;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "drama_id", nullable = false)
    private Drama drama;

    @Builder
    public Review(String contents, Integer star, User user, Drama drama) {
        this.contents = contents;
        this.star = star;
        this.user = user;
        this.drama = drama;
    }
}
