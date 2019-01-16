package main.java.repository;

import main.java.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<RoleEntity,Long> {

    RoleEntity findUserRoleByName(String name);
}
