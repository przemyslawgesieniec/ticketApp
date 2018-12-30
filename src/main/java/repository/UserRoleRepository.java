package main.java.repository;

import main.java.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role,Long> {

    Role findUserRoleByName(String name);
}
