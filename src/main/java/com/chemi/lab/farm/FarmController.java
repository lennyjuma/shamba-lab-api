package com.chemi.lab.farm;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/farm")
public class FarmController extends GenericController<Farm> {
    private final FarmService farmService;
    public FarmController(GenericRepository<Farm> repository, FarmService farmService) {
        super(repository);
        this.farmService = farmService;
    }

    @GetMapping("device")
    public List<Farm> getFarmsByDeviceID(@RequestParam(name = "device_id") String device_id) {
        return farmService.getFarmsByDeviceID(device_id);
    }
}
