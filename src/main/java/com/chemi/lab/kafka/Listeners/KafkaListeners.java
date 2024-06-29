package com.chemi.lab.kafka.Listeners;


import com.chemi.lab.air.AirService;
import com.chemi.lab.battery.BatteryService;
import com.chemi.lab.farm.FarmService;
import com.chemi.lab.gps.GpsService;
import com.chemi.lab.soil.SoilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaListeners {

    private final AirService airService;
    private final FarmService farmService;
    private final BatteryService batteryService;
    private final GpsService gpsService;
    private final SoilService soilService;

    @KafkaListener(topics = "shamba_lab")
    void shambaLabListener(String data) {
        log.info(data);

    }

}
