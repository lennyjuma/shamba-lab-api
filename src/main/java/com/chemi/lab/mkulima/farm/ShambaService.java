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
import com.chemi.lab.mkulima.farm.dto.ShambaBodyUpdateDto;
import com.chemi.lab.mkulima.farm.dto.ShambaBodydto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
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
        if (shambaRepo.findShambaByName(shamba.getName()).isPresent()){
            throw new ApiResourceNotFoundException("Shamba with name " + shamba.getName() + " already exists");
        }
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
        });

        return saved_farm;
    }

    public Shamba updateShamba(ShambaBodyUpdateDto shamba, String farmId) {
        Shamba farm = shambaRepo.findById(farmId).orElseThrow(() -> new ApiResourceNotFoundException("Farm with id " + farmId + " not found"));
        farm.setName(shamba.getName());
        farm.setLocation(shamba.getLocation());
        if(Objects.equals(shamba.getFarmingType(), "Mono cropping")){
            farm.setFarmingType(FarmType.MonoFarming);
        }
        else if(Objects.equals(shamba.getFarmingType(), "Mixed cropping")){
            farm.setFarmingType(FarmType.MixedFarming)  ;
        }

        shamba.getAddedCrops().forEach(farm_crop -> {
            Crop crop = cropRepo.findById(farm_crop).orElseThrow(() -> new ApiResourceNotFoundException("Crop with id " + farm_crop + " not found"));
            FarmCrop farmCrop = new FarmCrop();
            FarmCropEmbeddable farmCropEmbeddable = new FarmCropEmbeddable();
            farmCropEmbeddable.setCropId(crop.getId());
            farmCropEmbeddable.setShambaId(farm.getId());
            farmCrop.setId(farmCropEmbeddable);
            farmCrop.setCrop(crop);
            farmCrop.setShamba(farm);
            farmCropRepo.save(farmCrop);
        });
        shamba.getRemovedCrops().forEach(farm_crop -> {
            FarmCropEmbeddable farmCropEmbeddable = new FarmCropEmbeddable();
            farmCropEmbeddable.setCropId(farm_crop);
            farmCropEmbeddable.setShambaId(farm.getId());
            FarmCrop farmCrop = farmCropRepo.findById(farmCropEmbeddable).get();
            farmCropRepo.delete(farmCrop);
        });

        return shambaRepo.save(farm);
    }

    public List<Shamba> fetchShambasByCustomerId() {
        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        log.info("user_id: {}", user_id);
        return shambaRepo.findShambasByCustomer_IdOrderByCreatedAtAsc(user_id).orElseThrow(
                () -> new ApiResourceNotFoundException("Customer with id " + user_id + " not found")
        );
    }
    public String getDefaultFarmID(String farmId) { // get default id when query param is null
        if (farmId == null) {
            List<Shamba> shambaList = fetchShambasByCustomerId();
            if (shambaList.isEmpty()) {
                throw new ApiResourceNotFoundException("You do not have any farms yet");
            }
            farmId = shambaList.get(0).getId();
        }
        return farmId;
    }
    public Shamba fetchShambaByNameAndPhoneNUmber( String name) {
//        String user_id = securityContextMapper.getLoggedInCustomer().getId();
        return shambaRepo.findShambaByName(name).orElseThrow(
                //todo send mqtt to stm here
                () -> new ResourceNotFoundException("Shamba  with name " + name + " not found")
        );
    }

    public void deleteShamba(String farmId) {
        shambaRepo.deleteById(farmId);
    }
}
