package main.java.entity;

import main.java.dto.EventDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "name")
    private String eventName;

    @Column(name = "date")
    private Date eventDate;

    @Column(name = "description")
    private String eventDescription;


    public EventDto toDto(){
        return EventDto
                .builder()
                .eventDate(eventDate)
                .description(eventDescription)
                .name(eventName)
                .build();
    }
}
