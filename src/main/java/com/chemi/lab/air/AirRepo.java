package com.chemi.lab.air;

import com.chemi.lab.generics.GenericRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AirRepo extends GenericRepository<Air> {
    Optional<Air> findByShamba_IdOrderByCreatedAtDesc(String farm_id, Limit limit);
    Optional<Page<Air>> findByShamba_IdOrderByCreatedAtDesc(String device_id, PageRequest pg);
    Optional<Page<Air>> findByShamba_IdAndReadingDateBetweenOrderByCreatedAtDesc(String device_id, LocalDateTime start, LocalDateTime end, PageRequest pg);

}
