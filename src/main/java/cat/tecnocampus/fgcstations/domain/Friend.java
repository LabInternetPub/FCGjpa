package cat.tecnocampus.fgcstations.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Friend {
    @EmbeddedId
    private FriendId id;

    public String getUsername() {
        return id.getUsername();
    }

    public String getFriend() {
        return id.getFriend();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friend friend)) return false;

        return id.equals(friend.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
