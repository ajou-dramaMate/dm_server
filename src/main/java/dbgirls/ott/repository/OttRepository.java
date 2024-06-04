package dbgirls.ott.repository;

import dbgirls.ott.domain.Ott;
import dbgirls.ott.domain.OttType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OttRepository extends JpaRepository<Ott, Long> {
    Optional<Ott> findByName(OttType ott);
}

