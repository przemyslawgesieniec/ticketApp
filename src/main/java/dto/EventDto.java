package main.java.dto;

import lombok.Builder;
import lombok.Data;
import main.java.entity.EventType;
import main.java.entity.UserEventEntity;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EventDto {

    private Long id;
    private String name;
    private Date eventDate;
    private String description;
    private List<UserEventEntity> users;
    private List<EventType> eventTypes;
    private Double cost;
}
