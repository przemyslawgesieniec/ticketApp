package main.java.repository;

import main.java.model.entity.Role;
import main.java.model.entity.User;
import main.java.model.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);
    VerificationToken getByToken(String token);
    VerificationToken findByUser(User user);
}
