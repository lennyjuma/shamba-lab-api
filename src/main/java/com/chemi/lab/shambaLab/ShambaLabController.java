package com.chemi.lab.shambaLab;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.soil.Soil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/shambalab")
public class ShambaLabController extends GenericController<ShambaLab> {
    private final ShambaLabService shambaLabService;
    public ShambaLabController(GenericRepository<ShambaLab> repository, ShambaLabService shambaLabService) {
        super(repository);
        this.shambaLabService = shambaLabService;
    }
    @GetMapping("device")
    public List<ShambaLab> getSoilByDeviceID(@RequestParam(name = "device_id") String device_id) {
        return shambaLabService.getShambaLabByDeviceID(device_id);
    }
}
