package com.chemi.lab.air;


import com.chemi.lab.auth.config.SecurityContextMapper;
import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.soil.SoilRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirService extends GenericService<Air> {
    private final AirRepo airRepository;
    private final SecurityContextMapper securityContextMapper;
    public AirService(GenericRepository<Air> repository, AirRepo airRepository, SecurityContextMapper securityContextMapper) {
        super(repository);
        this.airRepository = airRepository;
        this.securityContextMapper = securityContextMapper;
    }

    public Page<Air> getAirByDeviceID(String deviceId, Integer page, Integer size) {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        System.out.println("user_id: " + user_id);
        PageRequest pg = PageRequest.of(page,size);
        return airRepository.findByDeviceId(deviceId,pg).orElseThrow(
                () -> new RuntimeException("No Air data found for device_id: " + deviceId));
    }

    public Air getLatestAirByDeviceID(String deviceId) {
        return airRepository.findTopByDeviceIdOrderByCreatedAtDesc(deviceId).orElseThrow(
                () -> new RuntimeException("No Air data found for device_id: " + deviceId));
    }
}
