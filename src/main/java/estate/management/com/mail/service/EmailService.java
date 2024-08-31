package estate.management.com.mail.service;

import estate.management.com.mail.mailpayload.MailPayload;

public interface EmailService {

    String sendEmail(MailPayload mailPayload);

}
