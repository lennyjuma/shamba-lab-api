package com.chemi.lab.contact_us;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService extends GenericService<ContactUs> {
    public ContactUsService(GenericRepository<ContactUs> repository) {
        super(repository);
    }

    @Override
    public ContactUs create(ContactUs newDomain) {
        return super.create(newDomain.createNewInstance());
    }
}
