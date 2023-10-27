package cat.tecnocampus.fgcstations.application;


import cat.tecnocampus.fgcstations.application.DTOs.DayTimeStartDTO;
import cat.tecnocampus.fgcstations.application.DTOs.FavoriteJourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.FriendsDTO;
import cat.tecnocampus.fgcstations.application.exception.UserDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.*;
import cat.tecnocampus.fgcstations.persistence.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FgcService {
    private StationDAO stationDAO;
    private UserDAO userDAO;
    private FavoriteJourneyDAO favoriteJourneyDAO;
    private JourneyDAO journeyDAO;
    private FriendDAO friendDAO;

    private DayTimeStartDao dayTimeStartDao;

    public FgcService(StationDAO stationDAO, UserDAO userDAO,
                      FavoriteJourneyDAO favoriteJourneyDAO, JourneyDAO journeyDAO,
                      FriendDAO friendDAO, DayTimeStartDao dayTimeStartDao) {
        this.stationDAO = stationDAO;
        this.userDAO = userDAO;
        this.favoriteJourneyDAO = favoriteJourneyDAO;
        this.journeyDAO = journeyDAO;
        this.friendDAO = friendDAO;
        this.dayTimeStartDao = dayTimeStartDao;
    }

    public List<Station> getStations() {
        return stationDAO.findAll();
    }

    public Station getStation(String nom) {
        return stationDAO.findByName(nom).orElseThrow();
    }

    public User getUser(String username) {
        //get the user
        User user = userDAO.findById(username).orElseThrow(() -> new UserDoesNotExistsException("user " + username + " doesn't exist"));

        //get the user's favorite journey
        user.setFavoriteJourneyList(getFavoriteJourneys(username));

        return user;
    }

    public List<User> getUsers() {
        //get the users
        List<User> users = userDAO.findAll();

        //get the users' favorite journeys
        users.forEach(u -> u.setFavoriteJourneyList(favoriteJourneyDAO.findByUser(u)));

        return users;
    }

    public List<Journey> getAllJourneys() {
        var journeys = journeyDAO.findAll();
        return journeys;
    }
    public boolean existsUser(String username) {
        return userDAO.findById(username).isPresent();
    }

    public void addUserFavoriteJourney(String username, FavoriteJourneyDTO favoriteJourneyDTO) {  ////////////
        FavoriteJourney favoriteJourney = convertFavoriteJourneyDTO(favoriteJourneyDTO);
        favoriteJourneyDAO.save(favoriteJourney);
    }

    private void saveFavoriteJourney(FavoriteJourney favoriteJourney) {
        favoriteJourneyDAO.save(favoriteJourney);
    }

    public List<FavoriteJourney> getFavoriteJourneys(String username) {
        User user = userDAO.findById(username).orElseThrow(() -> new UserDoesNotExistsException("user " + username + " doesn't exist"));
        List<FavoriteJourney> favoriteJourneys = favoriteJourneyDAO.findByUser(user);
        favoriteJourneys.forEach(f -> f.setStartList(dayTimeStartDao.findByFavoriteJourney_Id(f.getId())));

        return favoriteJourneys;
    }

    private FavoriteJourney convertFavoriteJourneyDTO(FavoriteJourneyDTO favoriteJourneyDTO) {
        FavoriteJourney favoriteJourney = new FavoriteJourney();
        favoriteJourney.setId(UUID.randomUUID().toString());
        Journey journey = new Journey(stationDAO.findByName(favoriteJourneyDTO.getOrigin()).orElseThrow(),
                                      stationDAO.findByName(favoriteJourneyDTO.getDestination()).orElseThrow());
        favoriteJourney.setJourney(journey);

        List<DayTimeStart> dayTimeStarts = favoriteJourneyDTO.getDayTimes().stream().map(this::convertDayTimeStartDTO).collect(Collectors.toList());
        favoriteJourney.setDateTimeStarts(dayTimeStarts);

        return favoriteJourney;
    }

    private DayTimeStart convertDayTimeStartDTO(DayTimeStartDTO dayTimeStartDTO) {
        return new DayTimeStart(dayTimeStartDTO.getDayOfWeek(), dayTimeStartDTO.getTime(), UUID.randomUUID().toString());
    }

    public FriendsDTO getUserFriends(String username) {
        User user = userDAO.findById(username).orElseThrow(() -> new UserDoesNotExistsException("user " + username + " doesn't exist"));
        List<Friend> friends = friendDAO.findByIdUsername(username);
        return buildFriendsDTO(friends, username);
    }

    private FriendsDTO buildFriendsDTO(List<Friend> friends, String username) {
        FriendsDTO friendsDTO = new FriendsDTO();
        friendsDTO.setUsername(username);
        if (friends.size() == 0) {
            friendsDTO.setFriends(new ArrayList<>());
        }
        else {
            friendsDTO.setFriends(friends.stream().map(Friend::getFriend).toList());
        }
        return friendsDTO;
    }

    public List<Friend> getAllUserFriends() {
        return friendDAO.findAll();
    }

    public void saveFriends(FriendsDTO friendsDTO) {
        if (!existsUser(friendsDTO.getUsername())) {
            UserDoesNotExistsException e = new UserDoesNotExistsException("User " + friendsDTO.getUsername() + " doesn't exist");
            e.setUsername(friendsDTO.getUsername());
            throw e;
        }

        Friend friend = convertFriendsDTO(friendsDTO);
        friendDAO.save(friend); ///////////////////////////////////////////
    }

    private Friend convertFriendsDTO(FriendsDTO friendsDTO) {
        Friend friend = new Friend();
        List<String> friendsList = new ArrayList<>();
       // friend.setUsername(friendsDTO.getUsername());
        //friend.setFriends(friendsDTO.getFriends());
        return friend;
    }
}
