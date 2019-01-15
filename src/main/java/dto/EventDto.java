package main.java.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EventDto {

    private String name;
    private Date eventDate;
    private String description;
}
