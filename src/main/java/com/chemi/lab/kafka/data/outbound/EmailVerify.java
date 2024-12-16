package com.chemi.lab.kafka.data.outbound;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerify extends BaseMailRequest {
    private String email;
    private String fName;
    private String lName;
    private String otp;

}
