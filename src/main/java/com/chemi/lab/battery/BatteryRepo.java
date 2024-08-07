package com.chemi.lab.battery;

import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface BatteryRepo extends GenericRepository<Battery> {
    Optional<List<Battery>> findByDeviceId(String device_id);
}
