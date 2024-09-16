package com.chemi.lab.mkulima.farm;

import com.chemi.lab.mkulima.farm.dto.ShambaBodydto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/shamba")
public class ShambaController {
    private final ShambaService shambaService;

    public ShambaController(ShambaService shambaService) {
        this.shambaService = shambaService;
    }

    @PostMapping
    public ResponseEntity<Shamba> create(@RequestBody ShambaBodydto shamba) {
        return ResponseEntity.ok(shambaService.addShamba(shamba)) ;
    }

    @GetMapping
    public ResponseEntity<List<Shamba>> fetchShambas() {
        return ResponseEntity.ok(shambaService.fetchShambasByCustomerId());
    }
}
