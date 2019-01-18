package main.java.dto;

import lombok.Builder;
import lombok.Data;
import main.java.entity.EventEntity;
import main.java.entity.UserEventEntity;
import main.java.validator.constraintAnnotations.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
public class UserDto {

    private Long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    private String password;

    private String reCaptcha;

    private boolean isEnabled;

    private boolean blocked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        if (!getName().equals(userDto.getName())) return false;
        if (!getLastName().equals(userDto.getLastName())) return false;
        if (!getEmail().equals(userDto.getEmail())) return false;
        return getPassword().equals(userDto.getPassword());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }


}
