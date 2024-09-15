package com.chemi.lab.mkulima.farm;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.mkulima.farm.dto.ShambaBodydto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/shamba")
public class ShambaController {
    private final ShambaService shambaService;

    public ShambaController(ShambaService shambaService) {
        this.shambaService = shambaService;
    }

    @PostMapping
    public ResponseEntity<Shamba> create(@RequestBody ShambaBodydto shamba) {
        return shambaService.addShamba(shamba);
    }
}
