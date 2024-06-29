package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

@Service
public class SoilService extends GenericService<Soil> {
    public SoilService(GenericRepository<Soil> repository) {
        super(repository);
    }
}
