package main.java.entity;

import lombok.Getter;
import lombok.Setter;
import main.java.dto.UserDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    public User() {
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
    private Set<Role> roles = new HashSet<>();

    @Column(name = "enabled")
    private boolean enabled;

    public UserDto toDto() {
        return UserDto.builder()
                .email(email)
                .lastName(lastName)
                .password(password)
                .build();
    }
}