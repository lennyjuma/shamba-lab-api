package com.chemi.lab.auth.controllers;


import com.chemi.lab.auth.dto.AuthResponse;
import com.chemi.lab.auth.dto.LoggedInUserDto;
import com.chemi.lab.auth.dto.LoginRequest;
import com.chemi.lab.auth.dto.RegisterRequest;
import com.chemi.lab.auth.mapper.LoggedInUserMapper;
import com.chemi.lab.auth.service.CustomerService;
import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final CustomerService customerService;
    private final LoggedInUserMapper loggedInUserMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerCustomer(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(customerService.registerCustomer(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest
    ) {
        try {
            return ResponseEntity.ok(customerService.login(loginRequest));
        } catch (Exception e) {
            throw new ApiResourceNotFoundException("Email or password is incorrect");
        }
    }
    @PostMapping("/loggedInUser")
    public ResponseEntity<LoggedInUserDto> loginUser(
            @RequestBody LoginRequest loginRequest
    ) {
        try {
            AuthResponse login = customerService.login(loginRequest);
            String token = login.getToken();
            LoggedInUserDto userDto = loggedInUserMapper.getLoggedInUserDto();
            userDto.setToken(token);
            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
