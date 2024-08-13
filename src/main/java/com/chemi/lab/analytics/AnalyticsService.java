package com.chemi.lab.analytics;

import com.chemi.lab.soil.SoilRepo;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    private final SoilRepo soilRepository;

    public AnalyticsService(SoilRepo soilRepository) {
        this.soilRepository = soilRepository;
    }

    public AnalyticsService getAnalytics() {
        return null;
    }
}
