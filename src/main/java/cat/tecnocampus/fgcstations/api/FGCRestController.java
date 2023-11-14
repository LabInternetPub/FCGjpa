package cat.tecnocampus.fgcstations.api;

import cat.tecnocampus.fgcstations.application.DTOs.*;
import cat.tecnocampus.fgcstations.application.FgcService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/user")
    public UserDTO getUser(Principal principal) {
        return fgcService.getUser(principal.getName());
    }

    @GetMapping("/stations")
    public List<StationDTO> getStations() {
        return fgcService.getStations();
     }

    @GetMapping("/stations/{nom}")
    public StationDTO getStation(@PathVariable String nom) {
        return fgcService.getStationDTO(nom);
    }

    @PostMapping("/users/{userName}/favoriteJourney")
    public void postAddFavoriteJourney(@PathVariable String userName, @RequestBody @Valid FavoriteJourneyDTO favoriteJourney) {
        fgcService.addUserFavoriteJourney(userName, favoriteJourney);
    }

    @PostMapping("/user/favoriteJourney")
    public void postAddFavoriteJourneyMe(Principal principal, @RequestBody @Valid FavoriteJourneyDTO favoriteJourney) {
        fgcService.addUserFavoriteJourney(principal.getName(), favoriteJourney);
    }

    @GetMapping("/users/{userName}/favoriteJourneys")
    public List<FavoriteJourneyDTO> getFavoriteJourneys(@PathVariable String userName) {
        return fgcService.getFavoriteJourneysDTO(userName);
    }

    @GetMapping("/user/favoriteJourneys")
    public List<FavoriteJourneyDTO> getFavoriteJourneysMe(Principal principal) {
        return fgcService.getFavoriteJourneysDTO(principal.getName());
    }

    @GetMapping("/journeys")
    public List<JourneyDTO> getAllJourneys() {
        return fgcService.getAllJourneys();
    }

    @GetMapping("/users/{userName}/friends")
    public UserFriendsDTO getFriends(@PathVariable String userName) {
        return fgcService.getUserFriends(userName);
    }

   @GetMapping("/user/friends")
    public UserFriendsDTO getFriends(Principal principal) {
        return fgcService.getUserFriends(principal.getName());
    }

    @GetMapping("/users/friends")
    public List<UserFriendsDTO> getAllFriends() {
        return fgcService.getAllUserFriends();
    }

    @PostMapping("/users/friends")
    public void saveFriends(@RequestBody @Valid UserFriendsDTO userFriendsDTO) {
        fgcService.saveFriends(userFriendsDTO);
    }

    @PostMapping("/user/friends")
    public void saveFriendsMe(Principal principal, @RequestBody @Valid UserFriendsDTO userFriendsDTO) {
        if (!principal.getName().equals(userFriendsDTO.getUsername()))
            throw new IllegalArgumentException("Usernames are not the same");

        fgcService.saveFriends(userFriendsDTO);
    }
}
