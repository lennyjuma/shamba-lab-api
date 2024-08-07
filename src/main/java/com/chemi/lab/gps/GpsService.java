package com.chemi.lab.gps;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GpsService extends GenericService<Gps> {
    private final GpsRepo gpsRepository;
    public GpsService(GenericRepository<Gps> repository, GpsRepo gpsRepository) {
        super(repository);
        this.gpsRepository = gpsRepository;
    }

    public List<Gps> getGPSByDeviceID(String deviceId) {
        return gpsRepository.findByDeviceId(deviceId).orElseThrow(
                () -> new RuntimeException("No GPS data found for device_id: " + deviceId));
    }
}
