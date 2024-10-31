package com.chemi.lab.contact_us;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService extends GenericService<ContactUs> {

    private final KafkaTemplate<String, ContactUs> kafkaTemplate;
    public ContactUsService(GenericRepository<ContactUs> repository, KafkaTemplate<String, ContactUs> kafkaTemplate) {
        super(repository);
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public ContactUs create(ContactUs newDomain) {
        ContactUs contactUs = super.create(newDomain.createNewInstance());
        System.out.println("firstName " + contactUs.getFName());
        kafkaTemplate.send("contact_us",contactUs); // start contact us notification
        return contactUs;
    }
}
