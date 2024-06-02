package dbgirls.ott.repository;

import dbgirls.ott.domain.Drama;
import dbgirls.ott.dto.dramaDto.DramaDetailRes;
import dbgirls.ott.dto.dramaDto.LikedDramaRes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DramaRepository extends JpaRepository<Drama, Long> {
}
