package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, String> {

    List<User> findAll();

}
