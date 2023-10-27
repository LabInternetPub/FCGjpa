package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StationDAO extends JpaRepository<Station, String> {

    Optional<Station> findByName(String nom);

}
