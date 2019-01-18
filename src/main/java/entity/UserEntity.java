package main.java.entity;

import lombok.Getter;
import lombok.Setter;
import main.java.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "User")
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"), name = "user")
public class UserEntity {

    public UserEntity() {
        super();
        this.enabled = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_role_id")}
    )
    private Set<RoleEntity> roles = new HashSet<>();


    @Column(name = "enabled")
    private boolean enabled;

    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .isEnabled(enabled)
                .email(email)
                .name(firstName)
                .lastName(lastName)
                .password(password)
                .build();
    }

    public UserDto toSaveDto() {
        return UserDto.builder()
                .id(id)
                .name(firstName)
                .isEnabled(enabled)
                .email(email)
                .lastName(lastName)
                .build();
    }
}
