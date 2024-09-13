package com.chemi.lab.auth.controllers;

import com.chemi.lab.auth.dto.LoggedInUserDto;
import com.chemi.lab.auth.mapper.LoggedInUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final LoggedInUserMapper loggedInUserMapper;
    @GetMapping()
    public LoggedInUserDto getLoggedInUser() {

        return loggedInUserMapper.getLoggedInUserDto();
    }
}
