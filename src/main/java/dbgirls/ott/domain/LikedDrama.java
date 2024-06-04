package dbgirls.ott.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "liked")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikedDrama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "drama_id", nullable = false)
    private Drama drama;

    @Builder
    public LikedDrama(User user, Drama drama) {
        this.user = user;
        this.drama = drama;
    }
}
