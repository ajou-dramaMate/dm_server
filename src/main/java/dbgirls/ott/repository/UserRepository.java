package dbgirls.ott.repository;

import dbgirls.ott.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);
}
