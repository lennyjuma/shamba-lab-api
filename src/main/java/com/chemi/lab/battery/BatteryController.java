package com.chemi.lab.battery;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/battery")
public class BatteryController extends GenericController<Battery> {
    private final BatteryService batteryService;
    public BatteryController(GenericRepository<Battery> repository, BatteryService batteryService) {
        super(repository);
        this.batteryService = batteryService;
    }
    @GetMapping("device")
    public List<Battery> getBatteryByDeviceID(@RequestParam(name = "device_id") String device_id) {
        return batteryService.getBatteryByDeviceID(device_id);
    }
}
