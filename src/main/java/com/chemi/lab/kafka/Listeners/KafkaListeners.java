package com.chemi.lab.kafka.Listeners;


import com.chemi.lab.air.AirService;
import com.chemi.lab.battery.BatteryService;
import com.chemi.lab.farm.FarmService;
import com.chemi.lab.gps.GpsService;
import com.chemi.lab.kafka.query.TransformShambaLabQueue;
import com.chemi.lab.soil.SoilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaListeners {
    private final TransformShambaLabQueue transformShambaLabQueue;

    @KafkaListener(topics = "shamba_lab", groupId = "groupId")
    void shambaLabListener(String data) {
        System.out.println(data.toString());
        transformShambaLabQueue.transformShambaLabQueue(data);

    }

}
