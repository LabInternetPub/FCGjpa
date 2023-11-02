package cat.tecnocampus.fgcstations.application.mapper;

import cat.tecnocampus.fgcstations.application.DTOs.*;
import cat.tecnocampus.fgcstations.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class MapperHelper {
    public static UserDTO userToUserDTO(User user) {
        var favoriteJourneyDTOList = user.getFavoriteJourneyList().stream().map(fj -> favoriteJourneyToFavoriteJourneyDTO(fj)).toList();
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getName(), user.getSecondName(), user.getEmail(), favoriteJourneyDTOList);
        return userDTO;
    }

    public static FavoriteJourneyDTO favoriteJourneyToFavoriteJourneyDTO(FavoriteJourney favoriteJourney) {
        FavoriteJourneyDTO favoriteJourneyDTO = new FavoriteJourneyDTO();
        favoriteJourneyDTO.setDestination(favoriteJourney.getJourney().getDestination());
        favoriteJourneyDTO.setOrigin(favoriteJourney.getJourney().getOrigin());
        favoriteJourneyDTO.setId(favoriteJourney.getId());
        favoriteJourneyDTO.setDayTimes(favoriteJourney.getStartList().stream().map(s -> new DayTimeStartDTO(s.getDayOfWeek(), s.getTimeStart())).toList());
        return favoriteJourneyDTO;
    }

    public static JourneyDTO journeyToJourneyDTO(Journey journey) {
        return new JourneyDTO(journey.getOrigin(), journey.getDestination());
    }

    public static StationDTO stationToStationDTO(Station station) {
        return new StationDTO(station.getName(), station.getLongitud(), station.getLatitud());
    }

    public static UserFriendsDTO friendToFriendDTO(List<Friend> friends, String username) {
        UserFriendsDTO userFriendsDTO = new UserFriendsDTO();
        userFriendsDTO.setUsername(username);
        if (friends.size() == 0) {
            userFriendsDTO.setFriends(new ArrayList<>());
        }
        else {
            userFriendsDTO.setFriends(friends.stream().map(Friend::getFriend).toList());
        }
        return userFriendsDTO;
    }

    public static List<UserFriendsDTO> allUserFriendListToListUserFriendsDTO(List<Friend> friends) {
        Map<String, List<Friend>> friendsMap =  friends.stream().collect(groupingBy(Friend::getUsername));
        List<UserFriendsDTO> result = new ArrayList<>(friendsMap.size());
        friendsMap.keySet().stream().forEach(u -> result.add(MapperHelper.listOfAUserFriendsToUserFriendsDTO(friendsMap.get(u))));
        return result;
    }

    public static UserFriendsDTO listOfAUserFriendsToUserFriendsDTO(List<Friend> friends) {
        UserFriendsDTO uf = new UserFriendsDTO();
        uf.setUsername(friends.get(0).getUsername());
        uf.setFriends(friends.stream().map(Friend::getFriend).toList());
        return uf;
    }
    public static List<Friend> friendsDTOToUserListOfFriends(User user, UserFriendsDTO userFriendsDTO) {
        return userFriendsDTO.getFriends().stream().map(f -> new Friend(user,f)).toList();
    }



}
