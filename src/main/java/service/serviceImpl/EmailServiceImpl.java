package main.java.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    public static final String REGISTRATION_CONFIRMATION_URL = "http://localhost:8080/registrationConfirmation.html?token=";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(final String emailTo,
                          final String subject,
                          final String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    public void sendRegistrationConfirmationMessage(final String emailTo, final String verificationToken) {

        StringBuffer content = new StringBuffer();
        content.append("Welcome to ticketApp")
                .append("\n\n")
                .append("We are glad that you are with us.")
                .append("If you want to finish the registration process, click the link below:")
                .append("\n\n")
                .append(REGISTRATION_CONFIRMATION_URL).append(verificationToken);

        sendEmail(emailTo,"TicketApp registration confirmation",content.toString());

    }
}
