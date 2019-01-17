package main.java.service.serviceImpl;

import main.java.dto.EventDto;
import main.java.entity.EventEntity;
import main.java.repository.EventRepository;
import main.java.view.NewEventInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl {

    @Autowired
    private EventRepository eventRepository;

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

    private void validateEventInput(NewEventInput eventInput){



    }




}
