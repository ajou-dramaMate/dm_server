package dbgirls.ott.repository;

import dbgirls.ott.domain.LikedDrama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedDramaRepository extends JpaRepository<LikedDrama, Long> {
    List<LikedDrama> findByUserId(Long userId);
}
