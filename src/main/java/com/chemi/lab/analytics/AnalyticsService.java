package com.chemi.lab.analytics;

import com.chemi.lab.soil.Soil;
import com.chemi.lab.soil.SoilRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {
    private final SoilRepo soilRepository;
    public Analytics getAnalytics(@RequestParam String deviceID) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<Soil> soilList = soilRepository.findAllByDeviceId(deviceID).orElseThrow(
                    () -> new RuntimeException("No analytics found for device " + deviceID)
            );
        Analytics analytics = new Analytics();

        soilList.forEach(soil -> {
            Charts charts = new Charts();
            analytics.getNitrogen().setName("Nitrogen");
            analytics.getNitrogen().getData().add(getParseInt(soil.getNitrogen()));
            analytics.getPotassium().setName("Potassium");
            analytics.getPotassium().getData().add(getParseInt(soil.getPotassium()));
            analytics.getPhosphorus().setName("Phosphorus");
            analytics.getPhosphorus().getData().add(getParseInt(soil.getPhosphorous()));
            analytics.getConductivity().setName("Electrical conductivity");
            analytics.getConductivity().getData().add(getParseInt(soil.getConductivity()));
            analytics.getTemperature().setName("Temperature");
            analytics.getTemperature().getData().add(getParseInt(soil.getTemperature()));
            analytics.getMoisture().setName("Moisture");
            analytics.getMoisture().getData().add(getParseInt(soil.getMoisture()));
            analytics.getPH().setName("pH");
            analytics.getPH().getData().add(getParseInt(soil.getPH()));
            analytics.getCategories().add(soil.getReading_date());


        });

            return analytics;
    }

    private static BigDecimal getParseInt(String val) {
        return new BigDecimal(val);
    }
}
