package dbgirls.ott.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
