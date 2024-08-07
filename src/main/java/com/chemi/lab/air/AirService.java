package com.chemi.lab.air;


import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirService extends GenericService<Air> {
    private final AirRepo airRepository;
    public AirService(GenericRepository<Air> repository, AirRepo airRepository) {
        super(repository);
        this.airRepository = airRepository;
    }

    public List<Air> getAirByDeviceID(String deviceId) {
        return airRepository.findByDeviceId(deviceId).orElseThrow(
                () -> new RuntimeException("No Air data found for device_id: " + deviceId));
    }
}
