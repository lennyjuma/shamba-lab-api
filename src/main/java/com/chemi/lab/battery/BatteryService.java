package com.chemi.lab.battery;

import com.chemi.lab.air.Air;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;

public class BatteryService  extends GenericService<Battery> {
    public BatteryService(GenericRepository<Battery> repository) {
        super(repository);
    }
}
