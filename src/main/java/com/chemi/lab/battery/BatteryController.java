package com.chemi.lab.battery;

import com.chemi.lab.air.Air;
import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;

public class BatteryController extends GenericController<Battery> {
    public BatteryController(GenericRepository<Battery> repository) {
        super(repository);
    }
}
