package com.chemi.lab.shambaLab;

import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShambaLabRepo extends GenericRepository<ShambaLab> {
    Optional<List<ShambaLab>> findByDeviceId(String device_id);
    Optional<List<ShambaLab>> findByFarmId(String device_id);

}
