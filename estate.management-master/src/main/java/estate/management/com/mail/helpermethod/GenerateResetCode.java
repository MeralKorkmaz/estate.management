package estate.management.com.mail.helpermethod;


import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GenerateResetCode{


    public  String generateResetCode() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }

}
