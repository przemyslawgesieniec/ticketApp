package main.java.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserEventDto {

    private Long id;
    private Long userId;
    private Long eventId;
    private Integer state;
    private Long orderNumber;

}
