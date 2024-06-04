package dbgirls.ott.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ott_drama_relation")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OttDramaRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ott_drama_relation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ott_id", nullable = false)
    private Ott ott;

    @ManyToOne
    @JoinColumn(name = "drama_id", nullable = false)
    private Drama drama;

    @Builder
    public OttDramaRelation(Ott ott, Drama drama) {
        this.ott = ott;
        this.drama = drama;
    }
}
