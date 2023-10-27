package cat.tecnocampus.fgcstations.domain;


import cat.tecnocampus.fgcstations.domain.exceptions.SameOriginDestinationException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Journey {

    @EmbeddedId
    JourneyId id;

    public Journey(Station origin, Station destination) {
        if (origin.sameStation(destination)) {
            throw new SameOriginDestinationException();
        }

        this.id = new JourneyId(origin.getName(), destination.getName());
    }

    public Journey() {
    }

    public String getOrigin() {
        return id.getOriginName();
    }

    public String getDestination() {
        return id.getDestinationName();
    }

    @Override
    public String toString() {
        return "Journey{" +
                "id=" + id +
                ", origin=" + getOrigin() +
                ", destination=" + getDestination() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Journey journey)) return false;

        return id.equals(journey.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
