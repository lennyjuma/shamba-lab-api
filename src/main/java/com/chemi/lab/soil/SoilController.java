package com.chemi.lab.soil;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/soil")
public class SoilController extends GenericController<Soil> {
    public SoilController(GenericRepository<Soil> repository) {
        super(repository);
    }
}
