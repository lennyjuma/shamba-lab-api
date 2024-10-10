package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SoilRepo extends GenericRepository<Soil> {
    Optional<Soil> findByShamba_IdOrderByCreatedAtDesc(String device_id, Limit limit);
    Optional<Page<Soil>> findByShamba_IdOrderByCreatedAtDesc(String device_id, PageRequest pg);
    Optional<Page<Soil>> findByShamba_IdAndReadingDateBetweenOrderByCreatedAtDesc(String device_id, LocalDateTime start, LocalDateTime end, PageRequest pg);

}
