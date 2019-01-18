package main.java.service;

import main.java.dto.EventDto;
import main.java.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService  extends UserDetailsService {

    Optional<UserDto> findByEmail(String email);
    UserDto getUserByEmail(String email);
    UserDto save(UserDto registrationRequestParams);
    void activateUser(String email);
    List<EventDto> getAllRequestedTickets(UserDto user);
    List<EventDto> getAllBoughtTickets(UserDto user);
    List<EventDto> getAllRejectedTickets(UserDto user);
    boolean requestEvent(Long eventId, String email);
    void buyTicket(Long eventId, String email);

    List<UserDto> getAllUsers();
//    List<EventDto> getAllTicketsRequestedByUsers();
}
