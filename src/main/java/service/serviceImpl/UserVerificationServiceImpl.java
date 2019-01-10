package main.java.service.serviceImpl;

import main.java.dto.UserDto;
import main.java.model.entity.User;
import main.java.model.entity.VerificationToken;
import main.java.repository.VerificationTokenRepository;
import main.java.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserVerificationServiceImpl implements UserVerificationService {

    private final static int EXPIRY_TIME = 60 * 24;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;


    @Override
    public VerificationToken generateAndPersistToken(User user) {

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setExpiryDate(calculateExpiryDate());
        verificationToken.setUser(user);
        verificationToken.setToken(generateToken());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    @Override
    public Optional<UserDto> validateToken(String token) {

        //TODO: test this method
        VerificationToken verificationTokenFromRepository = getVerificationToken(token);
        if (verificationTokenFromRepository != null) {
            if (!isTokenExpired(verificationTokenFromRepository.getExpiryDate()))
                return Optional.of(verificationTokenFromRepository.getUser().toDto());
        }
        return Optional.empty();
    }


    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRY_TIME);
        return new Date(cal.getTime().getTime());
    }


    private VerificationToken getVerificationToken(String verificationToken){
        return verificationTokenRepository.getByToken(verificationToken);
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private boolean isTokenExpired(Date expiryDate) {
        Date currentDate = new Date();
        return currentDate.after(expiryDate);
    }
}
