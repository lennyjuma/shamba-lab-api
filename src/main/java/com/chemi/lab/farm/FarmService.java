package com.chemi.lab.farm;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;


@Service
public class FarmService extends GenericService<Farm> {
    public FarmService(GenericRepository<Farm> repository) {
        super(repository);
    }
}
