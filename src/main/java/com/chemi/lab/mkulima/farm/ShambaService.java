package com.chemi.lab.mkulima.farm;

import com.chemi.lab.generics.GenericRepository;
import com.chemi.lab.generics.GenericService;
import com.chemi.lab.mkulima.crop.Crop;
import com.chemi.lab.mkulima.crop.CropRepo;
import com.chemi.lab.mkulima.farm.dto.ShambaBodydto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShambaService {
    private final ShambaRepo shambaRepo;
    private final CropRepo cropRepo;

    public ResponseEntity<Shamba> addShamba(ShambaBodydto shamba) {
        Shamba farm = new Shamba();
        farm.setName(shamba.getName());
        farm.setLocation(shamba.getLocation());
        if(Objects.equals(shamba.getFarmingType(), "Mono cropping")){
            farm.setFarmingType(FarmType.MonoFarming);
        }
        else if(Objects.equals(shamba.getFarmingType(), "Mixed cropping")){
            farm.setFarmingType(FarmType.MixedFarming)  ;
        }
        Shamba saved_farm = shambaRepo.save(farm);
        shamba.getCrops().forEach(farm_crop -> {
            Crop crop = new Crop();
            crop.setName(farm_crop);
            crop.setShamba(saved_farm);
            Crop saved_crop = cropRepo.save(crop);
        });

        return null;
    }
}
