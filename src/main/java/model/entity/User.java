package main.java.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_user_roles",
            joinColumns = { @JoinColumn(name="user_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_role_id")}
    )
    private Set<UserRole> userRoles = new HashSet<>();

    @Column(name = "enabled")
    private boolean enabled;
}
