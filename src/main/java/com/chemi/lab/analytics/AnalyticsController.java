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
    public SoilAnalytics getSoilAnalytics(@RequestParam(required = false) String farm_id,
                                          @RequestParam(name = "size",required = false) Integer size,
                                          @RequestParam(name = "start", required = false) String start,
                                          @RequestParam(name = "end", required = false) String end) throws Exception {
        return analyticsService.getSoilAnalytics(farm_id,size,start,end);
    }

    @GetMapping("air")
    public AirAnalytics getAirAnalytics(@RequestParam(required = false) String farm_id,
                                         @RequestParam(name = "size",required = false) Integer size,
                                        @RequestParam(name = "start", required = false) String start,
                                        @RequestParam(name = "end", required = false) String end) throws Exception {
        return analyticsService.getAirAnalytics(farm_id,size,start,end);
    }
}
