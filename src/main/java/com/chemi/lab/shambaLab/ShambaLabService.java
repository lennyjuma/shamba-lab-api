package com.chemi.lab.shambaLab;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

@Service
public class ShambaLabService extends GenericService<ShambaLab> {
    public ShambaLabService(GenericRepository<ShambaLab> repository) {
        super(repository);
    }
}
