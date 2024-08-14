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


    @GetMapping
    public Analytics getAnalytics(@RequestParam String deviceID,
                                  @RequestParam(name = "size",required = false) Integer size,
                                  @RequestParam(name = "farm",required = false) String farm,
                                  @RequestParam(name = "date",required = false) String date) {
        return analyticsService.getAnalytics(deviceID,size);
    }
}
