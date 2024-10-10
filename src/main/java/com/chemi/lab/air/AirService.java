package com.chemi.lab.air;


import com.chemi.lab.auth.config.SecurityContextMapper;
import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import com.chemi.lab.mkulima.farm.ShambaService;
import com.chemi.lab.utils.DateFormater;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AirService extends GenericService<Air> {
    private final AirRepo airRepository;
    private final ShambaService shambaService;
    private final DateFormater dateFormater;
    private final SecurityContextMapper securityContextMapper;
    public AirService(GenericRepository<Air> repository, AirRepo airRepository, ShambaService shambaService, DateFormater dateFormater, SecurityContextMapper securityContextMapper) {
        super(repository);
        this.airRepository = airRepository;
        this.shambaService = shambaService;
        this.dateFormater = dateFormater;
        this.securityContextMapper = securityContextMapper;
    }

    public Page<Air> getAirByDeviceID(String farmId, Integer page, Integer size, String startDate, String endDate) {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        System.out.println("user_id: " + user_id);
        PageRequest pg = PageRequest.of(page,size);

        LocalDateTime start = dateFormater.rangeDate(startDate, "start");
        LocalDateTime end = dateFormater.rangeDate(endDate, "end");
        return airRepository.findByShamba_IdAndReadingDateBetweenOrderByCreatedAtDesc(farmId,start,end,pg).orElseThrow(
                () -> new ApiResourceNotFoundException(String.format("Air data for farm with id %s not found",finalFarmId)));
    }

    public Air getLatestAirByDeviceID(String farmId) {
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        return airRepository.findByShamba_IdOrderByCreatedAtDesc(farmId, Limit.of(1)).orElseThrow(
                () -> new ApiResourceNotFoundException(String.format("Air data for farm with id %s not found", finalFarmId)));
    }
}
