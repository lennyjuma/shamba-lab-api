package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilService extends GenericService<Soil> {
    private final SoilRepo soilRepository;
    public SoilService(GenericRepository<Soil> repository, SoilRepo soilRepository) {
        super(repository);
        this.soilRepository = soilRepository;
    }


    public List<Soil> getSoilByDeviceID(String deviceId) {
        return soilRepository.findByDeviceId(deviceId).orElseThrow(
                () -> new RuntimeException("No Soil data found for device_id: " + deviceId));
    }
}
