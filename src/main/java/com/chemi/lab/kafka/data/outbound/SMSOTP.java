package com.chemi.lab.kafka.data.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMSOTP {
    private String phoneNumber;
    private String otp;
}
