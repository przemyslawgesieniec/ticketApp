package main.java.repository;

import main.java.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findUserByEmail(String email);
    UserEntity getUserByEmail(String email);
    UserEntity getUserEntitiesById(Long id);

}
