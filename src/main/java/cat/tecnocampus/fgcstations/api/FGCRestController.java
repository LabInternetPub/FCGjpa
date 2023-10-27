package cat.tecnocampus.fgcstations.api;

import cat.tecnocampus.fgcstations.application.DTOs.FavoriteJourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.FriendsDTO;
import cat.tecnocampus.fgcstations.application.FgcService;
import cat.tecnocampus.fgcstations.domain.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class FGCRestController {
    private FgcService fgcController;

    public FGCRestController(FgcService fgcController) {
        this.fgcController = fgcController;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return fgcController.getUsers();
    }

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username) {
        return fgcController.getUser(username);
    }

    @GetMapping("/stations")
    public List<Station> getStations() {
        return fgcController.getStations();
     }

    @GetMapping("/stations/{nom}")
    public Station getStation(@PathVariable String nom) {
        return fgcController.getStation(nom);
    }

    @PostMapping("/users/{userName}/favoriteJourney")
    public void postAddFavoriteJourney(@PathVariable String userName, @RequestBody @Valid FavoriteJourneyDTO favoriteJourney) {
        fgcController.addUserFavoriteJourney(userName, favoriteJourney);
    }

    @GetMapping("/users/{userName}/favoriteJourneys")
    public List<FavoriteJourney> getFavoriteJourneys(@PathVariable String userName) {
        return fgcController.getFavoriteJourneys(userName);
    }

    @GetMapping("/journeys")
    public List<Journey> getAllJourneys() {
        return fgcController.getAllJourneys();
    }

    @GetMapping("/users/{userName}/friends")
    public FriendsDTO getFriends(@PathVariable String userName) {
        return fgcController.getUserFriends(userName);
    }

    @GetMapping("/users/friends")
    public List<Friend> getAllFriends() {
        return fgcController.getAllUserFriends();
    }

    @PostMapping("/users/friends")
    public void saveFriends(@RequestBody @Valid FriendsDTO friendsDTO) {
        fgcController.saveFriends(friendsDTO);
    }
}
