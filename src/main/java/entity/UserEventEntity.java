package main.java.entity;

import lombok.Getter;
import lombok.Setter;
import main.java.dto.UserEventDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "user_event")
public class UserEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "state")
    private Integer state = 0;

    @Column(name = "orderNumber")
    private String orderNumber;


    public UserEventDto toDto(){
        return UserEventDto.builder()
                .eventId(eventId)
                .id(id)
                .orderNumber(orderNumber)
                .state(state)
                .userId(userId)
                .build();
    }
}
