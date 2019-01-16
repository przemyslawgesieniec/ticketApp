package main.java.dto;

import lombok.Builder;
import lombok.Data;
import main.java.entity.User;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EventDto {

    private String name;
    private Date eventDate;
    private String description;
    private List<User> users;
}
