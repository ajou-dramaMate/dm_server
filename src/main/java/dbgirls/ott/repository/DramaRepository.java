package dbgirls.ott.repository;

import dbgirls.ott.domain.Drama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DramaRepository extends JpaRepository<Drama, String> {
}
