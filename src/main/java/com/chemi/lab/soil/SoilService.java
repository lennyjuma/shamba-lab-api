package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class SoilService extends GenericService<Soil> {
    private final SoilRepo soilRepository;
    public SoilService(GenericRepository<Soil> repository, SoilRepo soilRepository) {
        super(repository);
        this.soilRepository = soilRepository;
    }


    public Page<Soil> getSoilByDeviceID(String deviceId,Integer page,Integer size) {
        PageRequest pg = PageRequest.of(page,size);
        return soilRepository.findByDeviceId(deviceId,  pg).orElseThrow(
                () -> new RuntimeException("No Soil data found for device_id: " + deviceId));
    }

    public Soil getLatestSoilByDeviceID(String deviceId) {
        return soilRepository.findTopByDeviceIdOrderByCreatedAtDesc(deviceId).orElseThrow(
                () -> new RuntimeException("No Soil data found for device_id: " + deviceId));
    }
}
