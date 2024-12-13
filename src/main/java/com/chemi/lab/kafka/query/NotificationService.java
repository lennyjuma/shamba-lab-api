package com.chemi.lab.kafka.query;

import com.chemi.lab.air.Air;
import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.kafka.data.outbound.SoilResultSMSNotification;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.utils.DateFormater;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final KafkaTemplate<String, SoilResultSMSNotification> kafkaTemplate;
    private final DateFormater dateFormater;

    public NotificationService(KafkaTemplate<String, SoilResultSMSNotification> kafkaTemplate, DateFormater dateFormater) {
        this.kafkaTemplate = kafkaTemplate;
        this.dateFormater = dateFormater;
    }

    public void sendSMS(Soil soil, String phone_number, Air air) {
        if (phone_number != null ) {
            if (phone_number.startsWith("0")){
                phone_number = "+254" +  phone_number.substring(1);
            } else if (phone_number.startsWith("254")){
                phone_number = "+" +  phone_number;
            }else {
                throw new ApiResourceNotFoundException("phone number provided is incorrect " + phone_number);
            }
        }else {
            throw new ApiResourceNotFoundException("phone number is required for msm notification");
        }
        SoilResultSMSNotification smsNotification = new SoilResultSMSNotification();
        smsNotification.setFName(soil.getShamba().getCustomer().getF_name());
        smsNotification.setLName(soil.getShamba().getCustomer().getL_name());
        smsNotification.setPhoneNumber(phone_number);
        smsNotification.setEmail(soil.getShamba().getCustomer().getEmail());
        smsNotification.setNitrogen(soil.getNitrogen());
        smsNotification.setPhosphorous(soil.getPhosphorous());
        smsNotification.setPotassium(soil.getPotassium());
        smsNotification.setPH(soil.getPH());
        smsNotification.setMoisture(soil.getMoisture());
        smsNotification.setConductivity(soil.getConductivity());
        smsNotification.setTemperature(soil.getTemperature());
        smsNotification.setDate(dateFormater.formatDate(soil.getReadingDate()));
        smsNotification.setFarm_name(soil.getShamba().getName());
        smsNotification.setAir_humidity(air.getHumidity());
        smsNotification.setAir_temperature( air.getTemperature());

        kafkaTemplate.send("sms-notification", smsNotification);

    }
}
