package com.chemi.lab.farm;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/farm")
public class FarmController extends GenericController<Farm> {
    public FarmController(GenericRepository<Farm> repository) {
        super(repository);
    }
}
