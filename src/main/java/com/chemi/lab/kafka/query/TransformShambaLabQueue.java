package com.chemi.lab.kafka.query;

import com.chemi.lab.air.Air;
import com.chemi.lab.battery.BatteryService;
import com.chemi.lab.farm.Farm;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.mkulima.farm.Shamba;
import com.chemi.lab.mkulima.farm.ShambaService;
import com.chemi.lab.shambaLab.ShambaLab;
import com.chemi.lab.shambaLab.ShambaLabService;
import com.chemi.lab.soil.Soil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransformShambaLabQueue {
    private final BatteryService batteryService;;
    private final ShambaLabService shambaLabService;
    private final ShambaService shambaService;
    private final NotificationService notificationService;

    public void transformShambaLabQueue(String queueMessage) {
        Map<String,Map<String,String>> shamba_lab = new HashMap<>();
        try {
            shamba_lab = new ObjectMapper().readValue(queueMessage,  new TypeReference<Map<String, Map<String, String>>>() {});
            Map<String, String> farm_obj = shamba_lab.get("Farm");
            String phone_number = farm_obj.get("Phone");
            String shamba_name = farm_obj.get("Name");
            Shamba shamba = getShambaByPhoneNumberAndName( shamba_name);
            Air air = getAir(shamba_lab);
            air.setShamba(shamba);
            Soil soil = getSoil(shamba_lab);
            soil.setShamba(shamba);
            Gps gps = getGps(shamba_lab);
            Farm farm = getFarm(shamba_lab);

            ShambaLab shambaLab = new ShambaLab();
            shambaLab.setAir(air);
            shambaLab.setDeviceId(farm_obj.get("deviceID"));
            shambaLab.setSoil(soil);
            shambaLab.setGps(gps);
            shambaLab.setFarm(farm);
            String readingDate = shamba_lab.get("GPS").get("Date") + " " + shamba_lab.get("GPS").get("Time");
            shambaLab.setReadingDate(getReadingDate(readingDate));

            ShambaLab lab = shambaLabService.create(shambaLab);
            notificationService.sendSMS(lab.getSoil(),phone_number,air); // send result sms notification



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static LocalDateTime getReadingDate(String readingDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d HH:mm");
        if (readingDate.length() < 13){
            return LocalDateTime.now();
        }
        return LocalDateTime.parse(readingDate, formatter);
    }

    private Shamba getShambaByPhoneNumberAndName(String name){
        return shambaService.fetchShambaByNameAndPhoneNUmber( name);
    }

    private static Air getAir(Map<String, Map<String, String>> shamba_lab) {
        Air air = new Air();
        air.setHumidity(shamba_lab.get("Air").get("Humidity"));
        air.setTemperature(shamba_lab.get("Air").get("Temperature"));
        air.setDeviceId(shamba_lab.get("Farm").get("deviceID"));
//        air.setCrop(shamba_lab.get("Farm").get("Crop"));
        String readingDate = shamba_lab.get("GPS").get("Date") + " " + shamba_lab.get("GPS").get("Time");
        air.setReadingDate(getReadingDate(readingDate));
        return air;
    }
    private static Gps getGps(Map<String, Map<String, String>> shamba_lab) {
        Gps gps = new Gps();
        gps.setLatitude(shamba_lab.get("GPS").get("Latitude"));
        gps.setLongitude(shamba_lab.get("GPS").get("Longitude"));
        gps.setDate(shamba_lab.get("GPS").get("Date"));
        gps.setTime(shamba_lab.get("GPS").get("Time"));
        gps.setDeviceId(shamba_lab.get("Farm").get("deviceID"));
        String readingDate = shamba_lab.get("GPS").get("Date") + " " + shamba_lab.get("GPS").get("Time");
        gps.setReadingDate(getReadingDate(readingDate));
        return gps;
    }

    private static Soil getSoil(Map<String, Map<String, String>> shamba_lab) {
        Soil soil = new Soil();
        soil.setNitrogen(shamba_lab.get("Soil").get("Nitrogen"));
        soil.setPhosphorous(shamba_lab.get("Soil").get("Phosphorus"));
        soil.setPotassium(shamba_lab.get("Soil").get("Potassium"));
        soil.setConductivity(shamba_lab.get("Soil").get("Conductivity"));
        soil.setMoisture(shamba_lab.get("Soil").get("Moisture"));
        soil.setTemperature(shamba_lab.get("Soil").get("Temperature"));
        soil.setPH(shamba_lab.get("Soil").get("pH"));
//        soil.setCrop(shamba_lab.get("Farm").get("Crop"));
        String readingDate = shamba_lab.get("GPS").get("Date") + " " + shamba_lab.get("GPS").get("Time");
        soil.setReadingDate(getReadingDate(readingDate));
        soil.setDeviceId(shamba_lab.get("Farm").get("deviceID"));
        return soil;
    }
    private static Farm getFarm(Map<String, Map<String, String>> shamba_lab) {
        Farm farm = new Farm();
//        farm.setCrop(shamba_lab.get("Farm").get("Crop"));
        farm.setPhone(shamba_lab.get("Farm").get("Phone"));
        farm.setDeviceId(shamba_lab.get("Farm").get("deviceID"));
        String readingDate = shamba_lab.get("GPS").get("Date") + " " + shamba_lab.get("GPS").get("Time");
        farm.setReadingDate(getReadingDate(readingDate));
        return farm;
    }
}
