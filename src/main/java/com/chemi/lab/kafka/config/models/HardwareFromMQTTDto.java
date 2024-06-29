package com.chemi.lab.kafka.config.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareFromMQTTDto {
    private Long fromID;
    private String valveId;
    private String meterImei;
    private String phoneNumber;
    private Integer amount;
}
