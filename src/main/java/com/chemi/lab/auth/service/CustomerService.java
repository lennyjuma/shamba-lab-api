package com.chemi.lab.auth.service;


import com.chemi.lab.auth.config.JwtService;
import com.chemi.lab.auth.dto.AuthResponse;
import com.chemi.lab.auth.dto.LoginRequest;
import com.chemi.lab.auth.dto.RegisterRequest;
import com.chemi.lab.auth.models.Customer;
import com.chemi.lab.auth.models.Role;
import com.chemi.lab.auth.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
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
                .build();
    }

    public AuthResponse registerCustomer(RegisterRequest registerRequest) {
        var customer = Customer.builder()
                .f_name(registerRequest.getF_name())
                .l_name(registerRequest.getL_name())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .build();
        customerRepository.save(customer);
        String jwtToken = jwtService.generateToken(customer);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
