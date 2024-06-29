package com.chemi.lab.farm;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;

public class FarmController extends GenericController<Farm> {
    public FarmController(GenericRepository<Farm> repository) {
        super(repository);
    }
}
