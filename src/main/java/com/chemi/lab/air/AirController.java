package com.chemi.lab.air;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/air")
public class AirController extends GenericController<Air> {

    private final AirService airService;

    public AirController(AirRepo repository, AirService airService) {
        super(repository);
        this.airService = airService;
    }





}
