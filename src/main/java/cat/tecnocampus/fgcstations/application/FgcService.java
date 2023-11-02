package cat.tecnocampus.fgcstations.application;


import cat.tecnocampus.fgcstations.application.DTOs.*;
import cat.tecnocampus.fgcstations.application.exception.UserDoesNotExistsException;
import cat.tecnocampus.fgcstations.application.mapper.MapperHelper;
import cat.tecnocampus.fgcstations.domain.*;
import cat.tecnocampus.fgcstations.persistence.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<StationDTO> getStations() {
        return stationDAO.findAll().stream().map(s -> new StationDTO(s.getName(), s.getLongitud(), s.getLatitud())).toList();
    }

    public StationDTO getStation(String nom) {
        return MapperHelper.stationToStationDTO(stationDAO.findByName(nom).orElseThrow());
    }

    public UserDTO getUser(String username) {
        //get the user
        User user = getDomainUser(username);
        user.setFavoriteJourneyList(getFavoriteJourneys(username));

        return MapperHelper.userToUserDTO(user);
    }

    private User getDomainUser(String username) {
        return userDAO.findById(username).orElseThrow(() -> new UserDoesNotExistsException("user " + username + " doesn't exist"));
    }

    public List<UserDTO> getUsers() {
        //get the users
        List<User> users = userDAO.findAll();

        //get the users' favorite journeys
        users.forEach(u -> u.setFavoriteJourneyList(getFavoriteJourneys(u.getUsername())));

        return users.stream().map(u -> MapperHelper.userToUserDTO(u)).toList();
    }

    private List<FavoriteJourney> getFavoriteJourneys(String username) {
        User user = getDomainUser(username);
        List<FavoriteJourney> favoriteJourneys = favoriteJourneyDAO.findByUser(user);
        favoriteJourneys.forEach(f -> f.setStartList(dayTimeStartDao.findByFavoriteJourney_Id(f.getId())));

        return favoriteJourneys;
    }

    public List<JourneyDTO> getAllJourneys() {
        return journeyDAO.findAll().stream().map(j -> MapperHelper.journeyToJourneyDTO(j)).toList();
    }

    public List<FavoriteJourneyDTO> getFavoriteJourneysDTO(String username) {
        User user = userDAO.findById(username).orElseThrow(() -> new UserDoesNotExistsException("user " + username + " doesn't exist"));
        List<FavoriteJourney> favoriteJourneys = favoriteJourneyDAO.findByUser(user);
        favoriteJourneys.forEach(f -> f.setStartList(dayTimeStartDao.findByFavoriteJourney_Id(f.getId())));

        return favoriteJourneys.stream().map(f -> MapperHelper.favoriteJourneyToFavoriteJourneyDTO(f)).toList();
    }
    public void addUserFavoriteJourney(String username, FavoriteJourneyDTO favoriteJourneyDTO) {
        FavoriteJourney favoriteJourney = convertFavoriteJourneyDTO(username, favoriteJourneyDTO);
        journeyDAO.save(favoriteJourney.getJourney());
        favoriteJourneyDAO.save(favoriteJourney);
        favoriteJourney.getStartList().forEach(dayTimeStartDao::save);
    }

    private FavoriteJourney convertFavoriteJourneyDTO(String username, FavoriteJourneyDTO favoriteJourneyDTO) {
        FavoriteJourney favoriteJourney = new FavoriteJourney();
        favoriteJourney.setUser(getDomainUser(username));
        favoriteJourney.setId(UUID.randomUUID().toString());
        Journey journey = new Journey(stationDAO.findByName(favoriteJourneyDTO.getOrigin()).orElseThrow(),
                stationDAO.findByName(favoriteJourneyDTO.getDestination()).orElseThrow());
        favoriteJourney.setJourney(journey);

        List<DayTimeStart> dayTimeStarts = favoriteJourneyDTO.getDayTimes().stream().map(dt -> convertDayTimeStartDTO(dt, favoriteJourney)).toList();
        favoriteJourney.setDateTimeStarts(dayTimeStarts);

        return favoriteJourney;
    }

    private DayTimeStart convertDayTimeStartDTO(DayTimeStartDTO dayTimeStartDTO, FavoriteJourney favoriteJourney) {
        return new DayTimeStart(dayTimeStartDTO.getDayOfWeek(), dayTimeStartDTO.getTime(), UUID.randomUUID().toString(), favoriteJourney);
    }

    public UserFriendsDTO getUserFriends(String username) {
        User user = getDomainUser(username);
        List<Friend> friends = friendDAO.findByIdUsername(username);
        return MapperHelper.listOfAUserFriendsToUserFriendsDTO(friends);
    }

    public List<UserFriendsDTO> getAllUserFriends() {
        return MapperHelper.allUserFriendListToListUserFriendsDTO(friendDAO.findAll());
    }

    public void saveFriends(UserFriendsDTO userFriendsDTO) {
        User user = userDAO.findById(userFriendsDTO.getUsername()).orElseThrow(() -> new UserDoesNotExistsException("User " + userFriendsDTO.getUsername() + " doesn't exist"));
        friendDAO.saveAll(MapperHelper.friendsDTOToUserListOfFriends(user, userFriendsDTO));
    }
}
