package cat.tecnocampus.fgcstations.security.entities;

import cat.tecnocampus.fgcstations.security.entities.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUsername(String email);
}
