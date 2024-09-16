package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import com.chemi.lab.mkulima.farm.ShambaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SoilService extends GenericService<Soil> {
    private final SoilRepo soilRepository;
    private final ShambaService shambaService;
    public SoilService(GenericRepository<Soil> repository, SoilRepo soilRepository, ShambaService shambaService) {
        super(repository);
        this.soilRepository = soilRepository;
        this.shambaService = shambaService;
    }


    public Page<Soil> getSoilByDeviceID(String farmId,Integer page,Integer size) {
        PageRequest pg = PageRequest.of(page,size);
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        return soilRepository.findByShamba_Id(farmId,  pg).orElseThrow(
                () -> new RuntimeException("No Soil data found for device_id: " + finalFarmId));
    }


    public Soil getLatestSoilByDeviceID(String farmId) {
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        return soilRepository.findTopByShamba_IdOrderByCreatedAtDesc(farmId).orElseThrow(
                () -> new RuntimeException("No Soil data found for device_id: " + finalFarmId));
    }
}
