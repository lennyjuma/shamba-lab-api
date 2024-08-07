package com.chemi.lab.air;

import com.chemi.lab.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface AirRepo extends GenericRepository<Air> {
    Optional<List<Air>> findByDeviceId(String device_id);

}
