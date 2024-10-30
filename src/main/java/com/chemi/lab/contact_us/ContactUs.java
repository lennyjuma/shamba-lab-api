package com.chemi.lab.contact_us;

import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.utils.PriKey;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ContactUs extends PriKey implements GenericEntity<ContactUs> {

    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;
    private String message;


    @Override
    public void update(ContactUs source) {
        setFName(source.getFName());
        setLName(source.getLName());
        setEmail(source.getEmail());
        setPhoneNumber(source.getPhoneNumber());
        setMessage(source.getMessage());
    }


    @Override
    public ContactUs createNewInstance() {
        ContactUs contactUs = new ContactUs();
        contactUs.update(this);
        return contactUs;
    }
}
