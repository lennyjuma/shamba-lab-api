package com.chemi.lab.soil;

import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/soil")
public class SoilController extends GenericController<Soil> {
    private final SoilService soilService;
    public SoilController(GenericRepository<Soil> repository, SoilService soilService) {
        super(repository);
        this.soilService = soilService;
    }
    @GetMapping("device")
    public Page<Soil> getSoilByDeviceID(@RequestParam(name = "device_id") String device_id,
                                        @RequestParam(name = "page") Integer page,
                                        @RequestParam(name = "size") Integer size) {
        return soilService.getSoilByDeviceID(device_id,page,size);
    }
}
