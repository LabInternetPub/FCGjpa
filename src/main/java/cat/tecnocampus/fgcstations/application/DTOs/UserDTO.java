package cat.tecnocampus.fgcstations.application.DTOs;

import java.util.List;

public record UserDTO(String username, String name, String secondname, String email, List<FavoriteJourneyDTO> favoriteJourneyList) {
}
