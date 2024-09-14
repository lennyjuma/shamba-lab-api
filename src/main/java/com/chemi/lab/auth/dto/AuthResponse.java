package com.chemi.lab.auth.dto;


import com.chemi.lab.mkulima.farm.Shamba;
import com.chemi.lab.shambaLab.ShambaLab;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Shamba> shambaList;
}
