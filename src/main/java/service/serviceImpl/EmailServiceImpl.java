package main.java.service.serviceImpl;

import com.itextpdf.text.DocumentException;
import main.java.dto.UserEventDto;
import main.java.service.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

@Service
public class EmailServiceImpl {

    public static final String REGISTRATION_CONFIRMATION_URL = "http://localhost:8080/registrationConfirmation.html?token=";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Invoice invoice;

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

    public void sendInvoice(String email, UserEventDto userEventDto, String eventName, Double price) throws FileNotFoundException, DocumentException, MessagingException {


        invoice.sendPdfFile(email,userEventDto,eventName,price);
    }
}
