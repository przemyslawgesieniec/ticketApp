package main.java.service.serviceImpl;

import main.java.dto.EventDto;
import main.java.entity.EventEntity;
import main.java.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public List<EventDto> getUserRequestedTickets() {
//
//
//    }
//
//    public List<EventDto> getUserBoughtTickets() {
//
//    }
}
