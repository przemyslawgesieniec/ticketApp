package main.java.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "user_event")
public class UserEventEntity  implements Serializable {

    @EmbeddedId
    private UserEventIdEntity id;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @Column(name = "state")
    private Boolean state;
}
