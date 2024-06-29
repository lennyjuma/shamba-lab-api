package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;

public class SoilController extends GenericController<Soil> {
    public SoilController(GenericRepository<Soil> repository) {
        super(repository);
    }
}
