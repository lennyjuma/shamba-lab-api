package com.chemi.lab.gps;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/gps")
public class GpsController extends GenericController<Gps> {
    public GpsController(GenericRepository<Gps> repository) {
        super(repository);
    }
}
