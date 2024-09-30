
package estate.management.com.contactmessage.mapper;

import estate.management.com.contactmessage.dto.ContactRequest;
import estate.management.com.contactmessage.dto.ContactResponse;
import estate.management.com.contactmessage.entity.Contact;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ContactMapper {

    public Contact requestToContact (ContactRequest contactRequest) {
        return Contact.builder()
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .eMail(contactRequest.getEMail())
                .message(contactRequest.getMessage())
                .status(0)
                .createAt(LocalDateTime.now())
                .build();
    }

    public ContactResponse contactToResponse (Contact contact) {
        return ContactResponse.builder()
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEMail())
                .message(contact.getMessage())
                .createAt(contact.getCreateAt())
                .build();
    }

}
