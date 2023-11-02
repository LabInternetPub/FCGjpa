package cat.tecnocampus.fgcstations.api;

import cat.tecnocampus.fgcstations.application.DTOs.*;
import cat.tecnocampus.fgcstations.application.FgcService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class FGCRestController {
    private final FgcService fgcService;

    public FGCRestController(FgcService fgcController) {
        this.fgcService = fgcController;
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return fgcService.getUsers();
    }

    @GetMapping("/users/{username}")
    public UserDTO getUser(@PathVariable String username) {
        return fgcService.getUser(username);
    }

    @GetMapping("/stations")
    public List<StationDTO> getStations() {
        return fgcService.getStations();
     }

    @GetMapping("/stations/{nom}")
    public StationDTO getStation(@PathVariable String nom) {
        return fgcService.getStation(nom);
    }

    @PostMapping("/users/{userName}/favoriteJourney")
    public void postAddFavoriteJourney(@PathVariable String userName, @RequestBody @Valid FavoriteJourneyDTO favoriteJourney) {
        fgcService.addUserFavoriteJourney(userName, favoriteJourney);
    }

    @GetMapping("/users/{userName}/favoriteJourneys")
    public List<FavoriteJourneyDTO> getFavoriteJourneys(@PathVariable String userName) {
        return fgcService.getFavoriteJourneysDTO(userName);
    }

    @GetMapping("/journeys")
    public List<JourneyDTO> getAllJourneys() {
        return fgcService.getAllJourneys();
    }

    @GetMapping("/users/{userName}/friends")
    public UserFriendsDTO getFriends(@PathVariable String userName) {
        return fgcService.getUserFriends(userName);
    }

    @GetMapping("/users/friends")
    public List<UserFriendsDTO> getAllFriends() {
        return fgcService.getAllUserFriends();
    }

    @PostMapping("/users/friends")
    public void saveFriends(@RequestBody @Valid UserFriendsDTO userFriendsDTO) {
        fgcService.saveFriends(userFriendsDTO);
    }
}
