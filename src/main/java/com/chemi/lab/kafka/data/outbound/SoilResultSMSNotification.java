package com.chemi.lab.kafka.data.outbound;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoilResultSMSNotification {
    private String email;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String nitrogen;
    private String phosphorous;
    private String potassium;
    private String conductivity;
    private String moisture;
    private String temperature;
    private String Date;
    private String air_temperature;
    private String air_humidity;
    private String pH;
    private String farm_name;
    private String latitude;
    private String longitude;
}
