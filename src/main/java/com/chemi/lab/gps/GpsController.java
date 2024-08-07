package com.chemi.lab.gps;

import com.chemi.lab.air.Air;
import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/gps")
public class GpsController extends GenericController<Gps> {
    private final GpsService gpsService;
    public GpsController(GenericRepository<Gps> repository, GpsService gpsService) {
        super(repository);
        this.gpsService = gpsService;
    }
    @GetMapping("device")
    public List<Gps> getGPSDataBYDeviceData(@RequestParam(name = "device_id") String device_id) {
        return gpsService.getGPSByDeviceID(device_id);
    }
}
