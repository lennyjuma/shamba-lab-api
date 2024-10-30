package com.chemi.lab.analytics;

import com.chemi.lab.air.Air;
import com.chemi.lab.air.AirRepo;
import com.chemi.lab.mkulima.farm.ShambaService;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.soil.SoilRepo;
import com.chemi.lab.utils.DateFormater;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final SoilRepo soilRepository;
    private final ShambaService shambaService;
    private final AirRepo airRepo;
    private final DateFormater dateFormater;
    public SoilAnalytics getSoilAnalytics( String farmId, Integer size, String startDate, String endDate) {
        PageRequest pg = PageRequest.of(0,size);
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        LocalDateTime start = dateFormater.rangeDate(startDate, "start");
        LocalDateTime end = dateFormater.rangeDate(endDate, "end");
        Page<Soil> soils = soilRepository.findByShamba_IdAndReadingDateBetweenOrderByCreatedAtDesc(farmId,start,end, pg).orElseThrow(
                () -> new RuntimeException("No analytics found for device " + finalFarmId)
        );
        SoilAnalytics soilAnalytics = new SoilAnalytics();

        soils.forEach(soil -> {
            soilAnalytics.getNitrogen().setName("Nitrogen");
            soilAnalytics.getNitrogen().getData().add(getParseInt(soil.getNitrogen()));
            soilAnalytics.getPotassium().setName("Potassium");
            soilAnalytics.getPotassium().getData().add(getParseInt(soil.getPotassium()));
            soilAnalytics.getPhosphorus().setName("Phosphorus");
            soilAnalytics.getPhosphorus().getData().add(getParseInt(soil.getPhosphorous()));
            soilAnalytics.getConductivity().setName("Electrical conductivity");
            soilAnalytics.getConductivity().getData().add(getParseInt(soil.getConductivity()));
            soilAnalytics.getTemperature().setName("Temperature");
            soilAnalytics.getTemperature().getData().add(getParseInt(soil.getTemperature()));
            soilAnalytics.getMoisture().setName("Moisture");
            soilAnalytics.getMoisture().getData().add(getParseInt(soil.getMoisture()));
            soilAnalytics.getPH().setName("pH");
            soilAnalytics.getPH().getData().add(getParseInt(soil.getPH()));
            soilAnalytics.getCategories().add(dateFormater.formatDate(soil.getReadingDate()));
            List<String> cropList = soil.getShamba().getFarmCrops().stream().map((farmCrop) -> farmCrop.getCrop().getName()).toList();
            soilAnalytics.setCrops(cropList);
        });

            return soilAnalytics;
    }

    private static BigDecimal getParseInt(String val) {
        return new BigDecimal(val);
    }

    public AirAnalytics getAirAnalytics(String farmId, Integer size, String startDate, String endDate) {
        PageRequest pg = PageRequest.of(0,size);
        farmId = shambaService.getDefaultFarmID(farmId);
        String finalFarmId = farmId;
        LocalDateTime start = dateFormater.rangeDate(startDate, "start");
        LocalDateTime end = dateFormater.rangeDate(endDate, "end");
        Page<Air> air_properties = airRepo.findByShamba_IdAndReadingDateBetweenOrderByCreatedAtDesc(farmId,start,end, pg).orElseThrow(
                () -> new RuntimeException("No analytics found for device " + finalFarmId)
        );
        AirAnalytics airAnalytics = new AirAnalytics();
        air_properties.forEach(air -> {
            airAnalytics.getTemp().setName("Nitrogen");
            airAnalytics.getTemp().getData().add(getParseInt(air.getTemperature()));
            airAnalytics.getHumidity().setName("Nitrogen");
            airAnalytics.getHumidity().getData().add(getParseInt(air.getHumidity()));
            airAnalytics.getCategories().add(dateFormater.formatDate(air.getReadingDate()));
            List<String> cropList = air.getShamba().getFarmCrops().stream().map((farmCrop) -> farmCrop.getCrop().getName()).toList();
            airAnalytics.setCrops(cropList);
        });
        return airAnalytics;
    }
}
