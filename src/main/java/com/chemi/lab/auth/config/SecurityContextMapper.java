package com.chemi.lab.auth.config;

import com.chemi.lab.auth.models.Customer;
import com.chemi.lab.auth.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class SecurityContextMapper {
    private final CustomerRepository customerRepository;
    public Customer getLoggedInCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        //        System.out.println("auth email "+ customer.getRole().toString());
        return customerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("Customer with the email: %s not found", email)));
    }
}
