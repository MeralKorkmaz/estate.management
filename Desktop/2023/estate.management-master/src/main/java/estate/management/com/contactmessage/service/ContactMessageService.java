
package estate.management.com.contactmessage.service;

import estate.management.com.contactmessage.dto.ContactRequest;
import estate.management.com.contactmessage.dto.ContactResponse;
import estate.management.com.contactmessage.entity.Contact;
import estate.management.com.contactmessage.mapper.ContactMapper;
import estate.management.com.contactmessage.messages.Messages;
import estate.management.com.contactmessage.repository.ContactMessageRepository;
import estate.management.com.exception.ResourceNotFoundException;
import estate.management.com.payload.response.ResponseMessage;
import estate.management.com.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ContactMapper contactMapper;
    private final PageableHelper pageableHelper;

    public ResponseMessage<ContactResponse> save(ContactRequest contactRequest) {

        Contact contact = contactMapper.requestToContact(contactRequest);
        Contact savedData = contactMessageRepository.save(contact);

        return ResponseMessage.<ContactResponse>builder()
                .message("Contact Message Created Successfully")
                .status(HttpStatus.CREATED)
                .object(contactMapper.contactToResponse(savedData))
                .build();
    }

    public Page<ContactResponse> getContactMessages(String q, Integer status, int page, int size, String sort, String type) {

        Pageable pageable = pageableHelper.getPageable(page, size, sort, type);
        return contactMessageRepository.getContactMessagesByCriteria(q,status,pageable)
                .map(contactMapper::contactToResponse);

    }

    public ContactResponse getContactMessageById(Long id) {

        Contact contact = contactMessageRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE));

        contact.setStatus(1);
        contactMessageRepository.save(contact);

        return contactMapper.contactToResponse(contact);

    }

    public String deleteById(Long id) {

        getContactMessageById(id);
        contactMessageRepository.deleteById(id);
        return Messages.CONTACT_MESSAGE_DELETED_SUCCESSFULLY;

    }
}
