package com.chemi.lab.auth.service;


import com.chemi.lab.auth.config.JwtService;
import com.chemi.lab.auth.dto.AuthResponse;
import com.chemi.lab.auth.dto.LoginRequest;
import com.chemi.lab.auth.dto.RegisterRequest;
import com.chemi.lab.auth.models.Customer;
import com.chemi.lab.auth.models.Role;
import com.chemi.lab.auth.repos.CustomerRepository;
import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.kafka.data.outbound.EmailVerify;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final KafkaTemplate<String, EmailVerify> kafkaTemplate;

    public AuthResponse login(LoginRequest loginRequest) { //use auth manager bean to authenticate
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                ) //this authenticates the user and throws an error if they are wrong
        );// it killd the thread if credentials are wrong so the other code won't run
        if (authenticate.isAuthenticated()){
            System.out.println("oooutttt!");
        }
        var customer = customerRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Customer not registered!")
        );
        String jwtToken = jwtService.generateToken(customer);
        return AuthResponse.builder()
                .token(jwtToken)
                .id(customer.getId())
                .f_name(customer.getF_name())
                .l_name(customer.getL_name())
                .email(customer.getEmail())
                .fullName(customer.getF_name() + " " + customer.getL_name())
                .shambaList(customer.getShambaList())
                .build();
    }

    public AuthResponse registerCustomer(RegisterRequest registerRequest) {
        var customer = Customer.builder()
                .f_name(registerRequest.getF_name())
                .l_name(registerRequest.getL_name())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .country(registerRequest.getCountry())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .build();
        boolean present = customerRepository.findByEmail(customer.getEmail()).isPresent();
        if (present){
            throw new ApiResourceNotFoundException("Email is taken, please login instead.");
        }
        Customer saved = customerRepository.save(customer);
        String jwtToken = jwtService.generateToken(customer);
        EmailVerify emailVerify = new EmailVerify();
        emailVerify.setEmail(saved.getEmail());
        emailVerify.setTo(saved.getEmail());
//        kafkaTemplate.send("verify_email",emailVerify);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
