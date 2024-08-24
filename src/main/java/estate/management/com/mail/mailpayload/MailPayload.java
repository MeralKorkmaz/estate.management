package estate.management.com.mail.mailpayload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailPayload {

    private String from;
    private String to;
    private String subject;
    private String text;
}
