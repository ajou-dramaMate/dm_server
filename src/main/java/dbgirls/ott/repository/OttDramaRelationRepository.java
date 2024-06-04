package dbgirls.ott.repository;

import dbgirls.ott.domain.OttDramaRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OttDramaRelationRepository extends JpaRepository<OttDramaRelation, Long> {
}
