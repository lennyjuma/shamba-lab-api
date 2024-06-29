package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;

public class SoilService extends GenericService<Soil> {
    public SoilService(GenericRepository<Soil> repository) {
        super(repository);
    }
}
