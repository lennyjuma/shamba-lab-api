package com.chemi.lab.air;

import com.chemi.lab.generics.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface AirRepo extends GenericRepository<Air> {
    Optional<List<Air>> findByShamba_IdOrderByCreatedAtDesc(String farm_id);
    Optional<Page<Air>> findByDeviceId(String device_id, PageRequest pg);
    Optional<Page<Air>> findByShamba_Id(String device_id, PageRequest pg);
    Optional<Air> findTopByDeviceIdOrderByCreatedAtDesc(String device_id);
    Optional<Air> findTopByShamba_IdOrderByCreatedAtDesc(String device_id);

}
