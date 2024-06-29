package com.chemi.lab.air;


import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

@Service
public class AirService extends GenericService<Air> {
    public AirService(GenericRepository<Air> repository) {
        super(repository);
    }
}
