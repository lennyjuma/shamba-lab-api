package com.chemi.lab.contact_us;

import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.utils.EmailValidator;
import com.chemi.lab.utils.PriKey;
import jakarta.persistence.Column;
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
    @Column(length = 65535, columnDefinition = "TEXT") // Increase size
    private String message;


    @Override
    public void update(ContactUs source) {
        setFName(source.getFName());
        setLName(source.getLName());
        String clientEmail = source.getEmail();
        String client_message = source.getMessage();
        if(!EmailValidator.isValidEmail(clientEmail)) {
            throw new ApiResourceNotFoundException("Email is not valid");
        }
        if (client_message.length() > 700 ) {
            throw new ApiResourceNotFoundException("Please shorten your message.");
        }
        setEmail(clientEmail);
        setPhoneNumber(source.getPhoneNumber());
        setMessage(client_message);
    }



    @Override
    public ContactUs createNewInstance() {
        ContactUs contactUs = new ContactUs();
        contactUs.update(this);
        return contactUs;
    }
}
