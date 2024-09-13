package com.chemi.lab.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String id;
    private String email;
    private String f_name;
    private String l_name;
    private String fullName;
    private String role;
}
