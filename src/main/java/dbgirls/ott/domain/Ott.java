package dbgirls.ott.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private OttType name;

    @Column(nullable = false)
    private Integer price;

    @Lob
    @Column(nullable = false, length = 100000)
    private byte[] ott_image;

    @OneToMany(mappedBy = "ott", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private final Set<OttDramaRelation> ottDramaRelations = new HashSet<>();

    @Builder
    public Ott(OttType name, Integer price, byte[] ott_image) {
        this.name = name;
        this.price = price;
        this.ott_image = ott_image;
    }
}
