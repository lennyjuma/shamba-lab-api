package com.chemi.lab.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInUserDto {

    private String id;
    private Long wallet_id;
    private String email;
    private String f_name;
    private String l_name;
    private String fullName;
    private String role;
    private String token;
}
