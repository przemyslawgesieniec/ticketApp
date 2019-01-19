package main.java.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import main.java.dto.UserEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


@Component
public class Invoice {

    private JavaMailSender mailSender;

    @Autowired
    public Invoice(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPdfFile(String emailAddress, UserEventDto userEventDto, String eventName, Double price) throws FileNotFoundException, DocumentException, MessagingException {

        String filename = userEventDto.getOrderNumber() + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        prepareInvoiceContent(document, font, userEventDto, eventName, price);
        document.close();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(emailAddress);
        helper.setSubject(("Invoice " + userEventDto.getOrderNumber()));
        helper.setText(String.format(
                "Invoice for your order with number: %s can be found in email's box",
                userEventDto.getOrderNumber())
        );
        helper.addAttachment(filename, new File(filename));

        mailSender.send(message);
    }

    private void prepareInvoiceContent(Document document, Font font, UserEventDto userEventDto, String eventName, Double price) throws DocumentException {
        document.add(new Paragraph("Invoice number " + userEventDto.getOrderNumber(), font));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Line item: ", font));
        document.add(new Paragraph(""));
        document.add(new Paragraph(eventName, font));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Total price: " + price, font));
        document.add(new Paragraph(""));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Thank you for shopping with us!", font));
    }
}
