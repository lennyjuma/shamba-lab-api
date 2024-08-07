package com.chemi.lab.battery;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BatteryService  extends GenericService<Battery> {
    private final BatteryRepo batteryRepo;
    public BatteryService(GenericRepository<Battery> repository, BatteryRepo batteryRepo) {
        super(repository);
        this.batteryRepo = batteryRepo;
    }


    public List<Battery> getBatteryByDeviceID(String deviceId) {
        return batteryRepo.findByDeviceId(deviceId).orElseThrow(
                () -> new RuntimeException("No Battery data found for device_id: " + deviceId));
    }
}
