package estate.management.com.contactmessage.controller;

import estate.management.com.contactmessage.dto.ContactRequest;
import estate.management.com.contactmessage.dto.ContactResponse;
import estate.management.com.contactmessage.service.ContactMessageService;
import estate.management.com.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contact-messages")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping
    public ResponseMessage<ContactResponse> save (@Valid @RequestBody ContactRequest contactRequest){
        return contactMessageService.save(contactRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public Page<ContactResponse> getContactMessages (
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "category_id") String sort,
            @RequestParam(value = "type", defaultValue = "asc") String type) {
        return contactMessageService.getContactMessages(q,status,page,size,sort,type);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<ContactResponse> getContactMessageById(@PathVariable Long id){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Manager')")
    public ResponseEntity<String> deleteById (@PathVariable Long id){
        return ResponseEntity.ok(contactMessageService.deleteById(id));
    }

}
