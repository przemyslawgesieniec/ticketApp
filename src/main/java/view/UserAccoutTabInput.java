package main.java.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main.java.dto.UserDto;
import main.java.entity.EventEntity;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserAccoutTabInput {

    private UserDto user;
    private List<EventEntity> boughtTickets;
    private List<EventEntity> requestedTickets;
}
