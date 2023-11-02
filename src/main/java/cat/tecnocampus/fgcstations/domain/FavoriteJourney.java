package cat.tecnocampus.fgcstations.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class FavoriteJourney {

    @Id
    private String id;

    @Transient
    private List<DayTimeStart> startList;

    @ManyToOne
    private Journey journey;

    @ManyToOne
    @JsonIgnore
    private User user;


    public FavoriteJourney() {
        startList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DayTimeStart> getStartList() {
        return startList;
    }

    public void setDateTimeStarts(List<DayTimeStart> startList) {
        this.startList = startList;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setStartList(List<DayTimeStart> startList) {
        this.startList = startList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FavoriteJourney{" +
                "id=" + id +
                //", startList=" + startList +
                ", journey=" + journey +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteJourney that = (FavoriteJourney) o;

        if (startList != null ? !startList.equals(that.startList) : that.startList != null) return false;
        return journey != null ? journey.equals(that.journey) : that.journey == null;
    }

    @Override
    public int hashCode() {
        int result = startList != null ? startList.hashCode() : 0;
        result = 31 * result + (journey != null ? journey.hashCode() : 0);
        return result;
    }
}
