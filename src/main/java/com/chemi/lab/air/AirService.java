package com.chemi.lab.air;


import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import com.chemi.lab.soil.Soil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirService extends GenericService<Air> {
    private final AirRepo airRepository;
    public AirService(GenericRepository<Air> repository, AirRepo airRepository) {
        super(repository);
        this.airRepository = airRepository;
    }

    public Page<Air> getAirByDeviceID(String deviceId, Integer page, Integer size) {
        PageRequest pg = PageRequest.of(page,size);
        return airRepository.findByDeviceId(deviceId,pg).orElseThrow(
                () -> new RuntimeException("No Air data found for device_id: " + deviceId));
    }

    public Air getLatestAirByDeviceID(String deviceId) {
        return airRepository.findTopByDeviceIdOrderByCreatedAtDesc(deviceId).orElseThrow(
                () -> new RuntimeException("No Air data found for device_id: " + deviceId));
    }
}
