package main.java.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEventIdEntity implements Serializable {

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "user_id")
    private Long userId;

}

