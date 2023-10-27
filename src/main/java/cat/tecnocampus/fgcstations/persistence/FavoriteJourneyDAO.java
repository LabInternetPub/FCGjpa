package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.FavoriteJourney;
import cat.tecnocampus.fgcstations.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteJourneyDAO extends JpaRepository<FavoriteJourney, String> {

    List<FavoriteJourney> findByUser(User user);
}
