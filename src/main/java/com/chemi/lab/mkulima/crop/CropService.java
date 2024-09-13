package com.chemi.lab.mkulima.crop;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;

public class CropService extends GenericService<Crop> {
    public CropService(GenericRepository<Crop> repository) {
        super(repository);
    }
}
