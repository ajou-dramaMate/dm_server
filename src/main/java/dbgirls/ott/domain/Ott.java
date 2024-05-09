package dbgirls.ott.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ott")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ott {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ott_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "ott", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private final Set<OttDramaRelation> ottDramaRelations = new HashSet<>();
}
