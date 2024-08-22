package estate.management.com.mail.controller;

import estate.management.com.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {

    private  EmailService emailService;
@Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

//    @PostMapping("/send")
//    public ResponseEntity<String> sendMail(){
//        return ResponseEntity.ok(emailService.sendEmail());
//    }

}
