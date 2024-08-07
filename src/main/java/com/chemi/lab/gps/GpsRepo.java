package com.chemi.lab.gps;

import com.chemi.lab.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface GpsRepo extends GenericRepository<Gps> {
    Optional<List<Gps>> findByDeviceId(String device_id);

}
