package main.java.repository;

import main.java.entity.User;
import main.java.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);
    VerificationToken getByToken(String token);
    VerificationToken findByUser(User user);
}
