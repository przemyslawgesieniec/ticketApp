package main.java.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "EventType")
@Table(name = "eventType")
@Getter
@Setter
public class EventType {

    @Id
    @Column(name = "event_id")
    private Long id;

    @Column(name = "type")
    private String eventType;

}
