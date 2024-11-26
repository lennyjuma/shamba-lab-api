package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/soil")
@Slf4j
public class SoilController extends GenericController<Soil> {
    private final SoilService soilService;
    public SoilController(GenericRepository<Soil> repository, SoilService soilService) {
        super(repository);
        this.soilService = soilService;
    }
    @GetMapping("device")
    public Page<Soil> getSoilByDeviceID(@RequestParam(name = "farm_id", required = false) String farm_id,
                                        @RequestParam(name = "page") Integer page,
                                        @RequestParam(name = "start", required = false) String start,
                                        @RequestParam(name = "end", required = false) String end,
                                        @RequestParam(name = "size") Integer size) {
        log.info("get latest by device");
        log.error("get soil by device error");
        return soilService.getSoilByShambaIDAndRangeDate(farm_id,page,size,start,end);
    }
    @GetMapping("latest")
    public Soil getLatestSoilByDeviceID(@RequestParam(name = "farm_id", required = false) String farm_id) {
        log.info("get latest by device");
        log.error("get soil by device error");
        return soilService.getLatestSoilByDeviceID(farm_id);
    }
}
