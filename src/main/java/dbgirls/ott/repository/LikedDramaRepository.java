package dbgirls.ott.repository;

import dbgirls.ott.domain.LikedDrama;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedDramaRepository extends JpaRepository<LikedDrama, Long> {
    Slice<LikedDrama> findByUserId(Pageable pageable, Long userId);

    LikedDrama findByDramaId(Long dramaId);

    Optional<LikedDrama> findByDramaIdAndUserId(Long userId, Long dramaId);
}
