package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.DayTimeStart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DayTimeStartDao extends JpaRepository<DayTimeStart, String> {
    List<DayTimeStart> findByFavoriteJourney_Id(String journeyId);
}
