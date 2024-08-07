package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface SoilRepo extends GenericRepository<Soil> {
    Optional<List<Soil>> findByDeviceId(String device_id);

}
