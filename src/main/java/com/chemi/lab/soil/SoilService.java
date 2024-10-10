package com.chemi.lab.soil;

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
public class SoilService extends GenericService<Soil> {
    private final SoilRepo soilRepository;
    private final DateFormater dateFormater;
    private final ShambaService shambaService;
    public SoilService(GenericRepository<Soil> repository, SoilRepo soilRepository, DateFormater dateFormater, ShambaService shambaService) {
        super(repository);
        this.soilRepository = soilRepository;
        this.dateFormater = dateFormater;
        this.shambaService = shambaService;
    }


    public Page<Soil> getSoilByShambaIDAndRangeDate(String farmId, Integer page, Integer size, String startDate, String endDate) {
        PageRequest pg = PageRequest.of(page,size);
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        LocalDateTime start = dateFormater.rangeDate(startDate, "start");
        LocalDateTime end = dateFormater.rangeDate(endDate, "end");
        return soilRepository.findByShamba_IdAndReadingDateBetweenOrderByCreatedAtDesc(farmId, start, end,  pg).orElseThrow(
                () -> new ApiResourceNotFoundException(String.format("Soil data for farm with id %s not found",finalFarmId)));
    }


    public Soil getLatestSoilByDeviceID(String farmId) {
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        return soilRepository.findByShamba_IdOrderByCreatedAtDesc(farmId, Limit.of(1)).orElseThrow(
                () -> new ApiResourceNotFoundException(String.format("Soil data for farm with id %s not found", finalFarmId)));
    }
}
