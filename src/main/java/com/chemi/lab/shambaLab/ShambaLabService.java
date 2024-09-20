package com.chemi.lab.shambaLab;

import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShambaLabService extends GenericService<ShambaLab> {
    private final ShambaLabRepo shambaLabRepo;
    public ShambaLabService(GenericRepository<ShambaLab> repository, ShambaLabRepo shambaLabRepo) {
        super(repository);
        this.shambaLabRepo = shambaLabRepo;
    }

    public List<ShambaLab> getShambaLabByDeviceID(String deviceId) {
        return shambaLabRepo.findByDeviceId(deviceId).orElseThrow(
                () -> new ApiResourceNotFoundException("No ShambaLab data found for device_id: " + deviceId));
    }
}
