package com.chemi.lab.auth.config;


import com.chemi.lab.auth.repos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService(){ //returns customer from db though customer implements user details
        return username -> customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found, check your email"));
    }

    @Bean
    public PasswordEncoder passEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    //Helps auth with just username and pasword auth during login...
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){ //sets which userdetails and passencoder to user
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passEncoder());
        return auth;
    }
}
