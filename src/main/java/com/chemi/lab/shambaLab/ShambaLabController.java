package com.chemi.lab.shambaLab;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/shambalab")
public class ShambaLabController extends GenericController<ShambaLab> {
    public ShambaLabController(GenericRepository<ShambaLab> repository) {
        super(repository);
    }
}
