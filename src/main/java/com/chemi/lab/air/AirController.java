package com.chemi.lab.air;

import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.soil.Soil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/air")
public class AirController extends GenericController<Air> {

    private final AirService airService;

    public AirController(AirRepo repository, AirService airService) {
        super(repository);
        this.airService = airService;
    }

    @GetMapping("device")
    public Page<Air> getAirDataBYDeviceData(@RequestParam(name = "farm_id",required = false) String farm_id,
                                            @RequestParam(name = "page") Integer page,
                                            @RequestParam(name = "start", required = false) String start,
                                            @RequestParam(name = "end", required = false) String end,
                                            @RequestParam(name = "size") Integer size) {
        return airService.getAirByDeviceID(farm_id,page,size,start,end);
    }
    @GetMapping("latest")
    public Air getLatestAirByDeviceID(@RequestParam(name = "farm_id",required = false) String farm_id) throws Exception {
        return airService.getLatestAirByDeviceID(farm_id);
    }
}
