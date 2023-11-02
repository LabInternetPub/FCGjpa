package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUsername(String email);
}
