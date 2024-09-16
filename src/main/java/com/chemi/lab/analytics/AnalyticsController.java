package com.chemi.lab.analytics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;


    @GetMapping("soil")
    public SoilAnalytics getSoilAnalytics(@RequestParam(required = false) String deviceID,
                                          @RequestParam(name = "size",required = false) Integer size,
                                          @RequestParam(name = "farm",required = false) String farm,
                                          @RequestParam(name = "date",required = false) String date) {
        return analyticsService.getSoilAnalytics(deviceID,size);
    }

    @GetMapping("air")
    public AirAnalytics getAirAnalytics(@RequestParam(required = false) String deviceID,
                                         @RequestParam(name = "size",required = false) Integer size,
                                         @RequestParam(name = "farm",required = false) String farm,
                                         @RequestParam(name = "date",required = false) String date) {
        return analyticsService.getAirAnalytics(deviceID,size);
    }
}
