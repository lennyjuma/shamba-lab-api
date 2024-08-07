package com.chemi.lab.farm;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FarmService extends GenericService<Farm> {

    private final FarmRepo farmRepository;

    public FarmService(GenericRepository<Farm> repository, FarmRepo farmRepository) {
        super(repository);
        this.farmRepository = farmRepository;
    }

    public List<Farm> getFarmsByDeviceID(String device_id) {
        return farmRepository.findByDeviceId(device_id).orElseThrow(
                () -> new RuntimeException("No Farm found for device_id: " + device_id)
        );
    }
}
