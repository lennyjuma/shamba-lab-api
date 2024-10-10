package com.chemi.lab.mkulima.farm;

import com.chemi.lab.auth.config.SecurityContextMapper;
import com.chemi.lab.auth.models.Customer;
import com.chemi.lab.auth.repos.CustomerRepository;
import com.chemi.lab.exceptions.ApiResourceNotFoundException;
import com.chemi.lab.mkulima.composite.FarmCrop;
import com.chemi.lab.mkulima.composite.FarmCropEmbeddable;
import com.chemi.lab.mkulima.composite.FarmCropRepo;
import com.chemi.lab.mkulima.crop.Crop;
import com.chemi.lab.mkulima.crop.CropRepo;
import com.chemi.lab.mkulima.farm.dto.ShambaBodydto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ShambaService {
    private final ShambaRepo shambaRepo;
    private final CropRepo cropRepo;
    private final CustomerRepository customerRepository;
    private final FarmCropRepo farmCropRepo;
    private final SecurityContextMapper securityContextMapper;

    public Shamba addShamba(ShambaBodydto shamba) {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        Shamba farm = new Shamba();
        Customer customer = customerRepository.findById(user_id).orElseThrow(
                () -> new ApiResourceNotFoundException("Customer with id "  + user_id + " not found")
        );
        farm.setCustomer(customer);
        farm.setStmName(customer.getPhoneNumber() + "_" + shamba.getName());
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
            Crop crop = cropRepo.findById(farm_crop).orElseThrow(() -> new ApiResourceNotFoundException("Crop with id " + farm_crop + " not found"));
            FarmCrop farmCrop = new FarmCrop();
            FarmCropEmbeddable farmCropEmbeddable = new FarmCropEmbeddable();
            farmCropEmbeddable.setCropId(crop.getId());
            farmCropEmbeddable.setShambaId(saved_farm.getId());
            farmCrop.setId(farmCropEmbeddable);
            farmCrop.setCrop(crop);
            farmCrop.setShamba(saved_farm);
            farmCropRepo.save(farmCrop);
//            saved_farm.getFarmCrops().add(farmCrop);
//            shambaRepo.save(saved_farm);
        });

        return saved_farm;
    }

    public List<Shamba> fetchShambasByCustomerId() {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        List<Shamba> shambas = shambaRepo.findShambasByCustomer_IdOrderByCreatedAtAsc(user_id).orElseThrow(
                () -> new ApiResourceNotFoundException("Customer with id " + user_id + " not found")
        );
        return shambas;
    }
    public String getDefaultFarmID(String farmId) { // get default id when query param is null
        if (farmId == null) {
            List<Shamba> shambaList = fetchShambasByCustomerId();
            farmId = shambaList.get(0).getId();
        }
        return farmId;
    }
    public Shamba fetchShambaByNameAndPhoneNUmber(String phoneNUmber, String name) {
//        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        return shambaRepo.findShambaByNameAndCustomer_PhoneNumber(name,phoneNUmber).orElseThrow(
                //todo send mqtt to stm here
                () -> new ResourceNotFoundException("Customer with id " + name + " not found")
        );
    }

    public Shamba updateShamba(ShambaBodydto shamba, String farmId) {
        Shamba farm = shambaRepo.findById(farmId).orElseThrow(() -> new ApiResourceNotFoundException("Farm with id " + farmId + " not found"));
        farm.setName(shamba.getName());
        farm.setLocation(shamba.getLocation());
        if(Objects.equals(shamba.getFarmingType(), "Mono cropping")){
            farm.setFarmingType(FarmType.MonoFarming);
        }
        else if(Objects.equals(shamba.getFarmingType(), "Mixed cropping")){
            farm.setFarmingType(FarmType.MixedFarming)  ;
        }
        return shambaRepo.save(farm);
    }
}
