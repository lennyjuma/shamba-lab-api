package com.chemi.lab.kafka.query;

import com.chemi.lab.air.Air;
import com.chemi.lab.air.AirService;
import com.chemi.lab.battery.BatteryService;
import com.chemi.lab.farm.Farm;
import com.chemi.lab.farm.FarmService;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.gps.GpsService;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.soil.SoilService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class TransformShambaLabQueue {

    private final AirService airService;
    private final FarmService farmService;
    private final BatteryService batteryService;
    private final GpsService gpsService;
    private final SoilService soilService;

    public void transformShambaLabQueue(String queueMessage) {
        Map<String,Map<String,String>> shamba_lab = new HashMap<>();
        try {
            shamba_lab = new ObjectMapper().readValue(queueMessage,  new TypeReference<Map<String, Map<String, String>>>() {});
            Air air = getAir(shamba_lab);
            Soil soil = getSoil(shamba_lab);
            Gps gps = getGps(shamba_lab);
            Farm farm = getFarm(shamba_lab);
            airService.create(air);
            farmService.create(farm);
            gpsService.create(gps);
            soilService.create(soil);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Air getAir(Map<String, Map<String, String>> shamba_lab) {
        Air air = new Air();
        air.setHumidity(shamba_lab.get("Air").get("Humidity"));
        air.setHumidity(shamba_lab.get("Temperature").get("Temperature"));
        return air;
    }
    private static Gps getGps(Map<String, Map<String, String>> shamba_lab) {
        Gps gps = new Gps();
        gps.setLatitude(shamba_lab.get("GPS").get("Latitude"));
        gps.setLongitude(shamba_lab.get("GPS").get("Longitude"));
        gps.setDate(shamba_lab.get("GPS").get("Date"));
        gps.setTime(shamba_lab.get("GPS").get("Time"));
        return gps;
    }

    private static Soil getSoil(Map<String, Map<String, String>> shamba_lab) {
        Soil soil = new Soil();
        soil.setNitrogen(shamba_lab.get("Soil").get("Nitrogen"));
        soil.setPhosphorous(shamba_lab.get("Soil").get("Phosphorous"));
        soil.setPhosphorous(shamba_lab.get("Soil").get("Potassium"));
        soil.setPhosphorous(shamba_lab.get("Soil").get("Conductivity"));
        soil.setPhosphorous(shamba_lab.get("Soil").get("Moisture"));
        soil.setPhosphorous(shamba_lab.get("Soil").get("Temperature"));
        soil.setPhosphorous(shamba_lab.get("Soil").get("pH"));
        return soil;
    }
    private static Farm getFarm(Map<String, Map<String, String>> shamba_lab) {
        Farm farm = new Farm();
        farm.setCrop(shamba_lab.get("Farm").get("Crop"));
        farm.setPhone(shamba_lab.get("Farm").get("Phone"));
        farm.setDeviceID(shamba_lab.get("Farm").get("deviceID"));
        return farm;
    }
}