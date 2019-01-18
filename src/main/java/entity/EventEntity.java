package main.java.entity;

import lombok.Getter;
import lombok.Setter;
import main.java.dto.EventDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "Event")
@Table(name = "event")
@Getter
@Setter
public class EventEntity {

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

    @Column(name = "cost")
    private Double eventCost;

    public EventDto toDto() {
        return EventDto
                .builder()
                .id(id)
                .eventDate(eventDate)
                .description(eventDescription)
                .name(eventName)
                .cost(eventCost)
                .build();
    }
}
