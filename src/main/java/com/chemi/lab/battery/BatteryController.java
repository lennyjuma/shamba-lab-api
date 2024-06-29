package com.chemi.lab.battery;

import com.chemi.lab.air.Air;
import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/battery")
public class BatteryController extends GenericController<Battery> {
    public BatteryController(GenericRepository<Battery> repository) {
        super(repository);
    }
}
