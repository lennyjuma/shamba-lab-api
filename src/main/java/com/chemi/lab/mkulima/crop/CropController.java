package com.chemi.lab.mkulima.crop;

import com.chemi.lab.generics.GenericController;
import com.chemi.lab.generics.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/crop")
public class CropController extends GenericController<Crop> {

    public CropController(GenericRepository<Crop> repository) {
        super(repository);
    }
}
