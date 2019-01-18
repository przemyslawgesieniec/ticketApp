package main.java.service.serviceImpl;

import main.java.dto.EventDto;
import main.java.dto.UserDto;
import main.java.entity.EventEntity;
import main.java.entity.RoleEntity;
import main.java.entity.UserEntity;
import main.java.entity.UserEventEntity;
import main.java.repository.EventRepository;
import main.java.repository.RoleRepository;
import main.java.repository.UserEventRepository;
import main.java.repository.UserRepository;
import main.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> user = userRepository.findUserByEmail(email);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new UserDetailsServiceImpl(user.get());
    }


    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findUserByEmail(email).map(UserEntity::toSaveDto);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.getUserByEmail(email).toDto();
    }

    @Override
    public UserDto save(UserDto registrationRequestParams) {

        UserEntity user = new UserEntity();
        RoleEntity role = roleRepository.findRoleByName("ROLE_USER");
        user.setEmail(registrationRequestParams.getEmail());
        user.setFirstName(registrationRequestParams.getName());
        user.setLastName(registrationRequestParams.getLastName());
        user.setPassword(passwordEncoder.encode(registrationRequestParams.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        return userRepository.save(user).toDto();
    }

    @Override
    public void activateUser(String email) {

        UserEntity user = userRepository.getUserByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public List<EventDto> getAllRequestedTickets(UserDto user) {
        return getAllTicketsByState(user,0);
    }

    @Override
    public List<EventDto> getAllBoughtTickets(UserDto user) {
        return getAllTicketsByState(user,1);
    }

    @Override
    public List<EventDto> getAllRejectedTickets(UserDto user) {
        return getAllTicketsByState(user,2);
    }


    //todo move this to different service
    @Override
    public boolean requestEvent(Long eventId, String email) {

        UserEntity userEntity = userRepository.getUserByEmail(email);
        EventEntity eventEntity = eventRepository.getById(eventId);

        Optional<UserEventEntity> userEventEntityOptional = userEventRepository.findOneByUserIdAndEventId(userEntity.getId(), eventEntity.getId());

        if(userEventEntityOptional.isPresent()){
            return false;
        }
        createUserEventEntity(eventEntity.getId(),userEntity.getId(),0);

        return true;
    }

    //todo move this to different service
    @Override
    public void buyTicket(Long eventId, String email) {

        UserEntity userEntity = userRepository.getUserByEmail(email);
        EventEntity eventEntity = eventRepository.getById(eventId);
        createUserEventEntity(eventEntity.getId(),userEntity.getId(),1);
    }

    @Override
    public void blockUser(Long id) {

        UserEntity userEntity = userRepository.getUserEntitiesById(id);
        userEntity.setBlocked(true);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserEntity::toSaveDto).collect(Collectors.toList());
    }

    @Override
    public void unblockUser(Long id) {
        UserEntity userEntity = userRepository.getUserEntitiesById(id);
        userEntity.setBlocked(false);
        userRepository.save(userEntity);
    }


    private void createUserEventEntity(Long eventId, Long userId, Integer state){
        UserEventEntity userEventEntity = new UserEventEntity();
        userEventEntity.setEventId(eventId);
        userEventEntity.setUserId(userId);
        userEventEntity.setState(state);
        userEventRepository.save(userEventEntity);
    }


    private List<EventDto> getAllTicketsByState(UserDto user, int state){
        List<UserEventEntity> eventEntityList = userEventRepository.getAllByUserIdAndState(user.getId(),state);
        List<Long> eventIdList = eventEntityList.stream().map(UserEventEntity::getEventId).collect(Collectors.toList());
        List<EventEntity> requestedEventList = eventRepository.findByIdIn(eventIdList);
        return requestedEventList.stream().map(EventEntity::toDto).collect(Collectors.toList());
    }
}
