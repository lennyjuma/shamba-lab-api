package com.chemi.lab.gps;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;

public class GpsService extends GenericService<Gps> {
    public GpsService(GenericRepository<Gps> repository) {
        super(repository);
    }
}
