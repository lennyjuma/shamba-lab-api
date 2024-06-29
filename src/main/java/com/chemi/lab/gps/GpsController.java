package com.chemi.lab.gps;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;

public class GpsController extends GenericController<Gps> {
    public GpsController(GenericRepository<Gps> repository) {
        super(repository);
    }
}
