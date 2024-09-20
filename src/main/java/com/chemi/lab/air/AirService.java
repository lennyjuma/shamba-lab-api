package com.chemi.lab.air;


import com.chemi.lab.auth.config.SecurityContextMapper;
import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import com.chemi.lab.mkulima.farm.ShambaService;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.soil.SoilRepo;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirService extends GenericService<Air> {
    private final AirRepo airRepository;
    private final ShambaService shambaService;
    private final SecurityContextMapper securityContextMapper;
    public AirService(GenericRepository<Air> repository, AirRepo airRepository, ShambaService shambaService, SecurityContextMapper securityContextMapper) {
        super(repository);
        this.airRepository = airRepository;
        this.shambaService = shambaService;
        this.securityContextMapper = securityContextMapper;
    }

    public Page<Air> getAirByDeviceID(String farmId, Integer page, Integer size) {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        System.out.println("user_id: " + user_id);
        PageRequest pg = PageRequest.of(page,size);
        return airRepository.findByShamba_Id(farmId,pg).orElseThrow(
                () -> new ApiResourceNotFoundException(String.format("Air data for farm with id %s not found",finalFarmId)));
    }

    public Air getLatestAirByDeviceID(String farmId) {
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        return airRepository.findTopByShamba_IdOrderByCreatedAtDesc(farmId).orElseThrow(
                () -> new ApiResourceNotFoundException(String.format("Air data for farm with id %s not found",finalFarmId)));
    }
}
