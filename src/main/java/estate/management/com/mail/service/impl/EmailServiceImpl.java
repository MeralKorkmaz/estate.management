package estate.management.com.mail.service.impl;

import estate.management.com.mail.mailpayload.MailPayload;
import estate.management.com.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendEmail(MailPayload mailPayload) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailPayload.getFrom());
        simpleMailMessage.setTo(mailPayload.getTo());
        simpleMailMessage.setSubject(mailPayload.getSubject());
        simpleMailMessage.setText(mailPayload.getText());
        javaMailSender.send(simpleMailMessage);
        return "Email sent successfully tarafidan";
    }


}
