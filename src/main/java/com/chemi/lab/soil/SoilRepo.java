package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SoilRepo extends GenericRepository<Soil> {
    Optional<Page<Soil>> findByDeviceId(String device_id, PageRequest pg);

}
