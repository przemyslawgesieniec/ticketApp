package main.java.model.entity;

import lombok.Data;
import main.java.model.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Long id;

    @Column(name = "user_role_name")
    private String name;

    @ManyToMany
    private Set<User> usersRoles = new HashSet<>();
}
