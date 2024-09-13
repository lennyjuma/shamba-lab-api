package com.chemi.lab.auth.mapper;

import com.chemi.lab.auth.config.SecurityContextMapper;
import com.chemi.lab.auth.dto.LoggedInUserDto;
import com.chemi.lab.auth.models.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedInUserMapper {
    private final SecurityContextMapper securityContextMapper;
    public LoggedInUserDto getLoggedInUserDto() {
        Customer customer = securityContextMapper.getLoggedInCustomer();
        LoggedInUserDto loggedInUserDto = new LoggedInUserDto();
        loggedInUserDto.setId(customer.getId());
        loggedInUserDto.setF_name(customer.getF_name());
        loggedInUserDto.setL_name(customer.getL_name());
        loggedInUserDto.setEmail(customer.getEmail());
        loggedInUserDto.setFullName(customer.getF_name() + " " + customer.getL_name());
        return loggedInUserDto;
    }
}
