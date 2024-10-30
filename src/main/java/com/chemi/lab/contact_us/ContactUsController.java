package com.chemi.lab.contact_us;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/contact-us")
public class ContactUsController extends GenericController<ContactUs> {

    private final ContactUsService contactUsService;

    public ContactUsController(GenericRepository<ContactUs> repository, ContactUsService contactUsService) {
        super(repository);
        this.contactUsService = contactUsService;
    }

    @Override
    @PostMapping("")
    public ResponseEntity<ContactUs> create(ContactUs created) {
        return ResponseEntity.ok(contactUsService.create(created));
    }
}
