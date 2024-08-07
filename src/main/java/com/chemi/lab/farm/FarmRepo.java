package com.chemi.lab.farm;

import com.chemi.lab.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepo extends GenericRepository<Farm> {
    Optional<List<Farm>> findByDeviceId(String device_id);

}
