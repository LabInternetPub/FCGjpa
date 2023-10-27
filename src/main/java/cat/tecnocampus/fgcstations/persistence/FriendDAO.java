package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.domain.Friend;
import cat.tecnocampus.fgcstations.domain.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendDAO extends JpaRepository<Friend, FriendId> {

    List<Friend> findByIdUsername(String username);

}
