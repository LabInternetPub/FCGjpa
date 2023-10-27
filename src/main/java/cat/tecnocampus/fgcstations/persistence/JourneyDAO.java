package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JourneyDAO extends JpaRepository<Journey, JourneyId> {

    @Override
    Optional<Journey> findById(JourneyId journeyId);

    @Query("""
            select j.id
            from Journey j
            where j.id.originName = :origin and j.id.destinationName = :destination
            """)
    Optional<JourneyId> findByOriginAndDestination(String origin, String destination);
}
