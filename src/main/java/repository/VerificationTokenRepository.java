package main.java.repository;

import main.java.entity.UserEntity;
import main.java.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity,Long> {

    VerificationTokenEntity findByToken(String token);
    VerificationTokenEntity getByToken(String token);
    VerificationTokenEntity findByUser(UserEntity user);
}
