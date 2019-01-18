package main.java.service.serviceImpl;

import main.java.dto.EventDto;
import main.java.entity.EventEntity;
import main.java.entity.UserEntity;
import main.java.entity.UserEventEntity;
import main.java.repository.EventRepository;
import main.java.repository.UserEventRepository;
import main.java.repository.UserRepository;
import main.java.service.UserService;
import main.java.view.NewEventInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEventRepository userEventRepository;

    public List<EventDto> getAllEvents() {

        return eventRepository.findAll()
                .stream()
                .map(EventEntity::toDto)
                .collect(Collectors.toList());
    }

    public EventDto addNewEvent(NewEventInput eventInput) throws ParseException, NumberFormatException {

        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventDate(NewEventInput.toDate(eventInput.getDate()));
        eventEntity.setEventName(eventInput.getName());
        eventEntity.setEventCost(NewEventInput.toDouble(eventInput.getCost()));
        eventEntity.setEventDescription(eventInput.getDescription());
        return eventRepository.save(eventEntity).toDto();
    }

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

    public void buyTicket(Long eventId, String email) {

        UserEntity userEntity = userRepository.getUserByEmail(email);
        EventEntity eventEntity = eventRepository.getById(eventId);
        createUserEventEntity(eventEntity.getId(),userEntity.getId(),1);
    }

    private void createUserEventEntity(Long eventId, Long userId, Integer state){
        UserEventEntity userEventEntity = new UserEventEntity();
        userEventEntity.setEventId(eventId);
        userEventEntity.setUserId(userId);
        userEventEntity.setState(state);
        userEventRepository.save(userEventEntity);
    }


}
