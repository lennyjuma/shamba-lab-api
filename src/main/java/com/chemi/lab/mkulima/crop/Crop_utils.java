package com.chemi.lab.mkulima.crop;

import com.chemi.lab.mkulima.composite.FarmCrop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Array;
import java.util.List;

@Configuration
public class Crop_utils {
    private final CropRepo cropRepo;

    public Crop_utils(CropRepo cropRepo) {
        this.cropRepo = cropRepo;
    }

    @Bean
    public List<String> AddCrop() {
        List<String> crops = List.of("Maize", "Beans", "Peas", "Avocado", "Kales", "Spinach", "Flowers","Sugarcane","Oranges","Coffe","Tea");
        if (checkIsTableEmpty()) {
            crops.forEach((crop) -> {
                cropRepo.save(Crop.CreateCrop(crop));
            });
        }
        return crops;
    }

    private Boolean checkIsTableEmpty(){
        return cropRepo.findAll().isEmpty();
    }
    public static List<Crop> getCropList(List<FarmCrop> farmCrop){
        return farmCrop.stream().map(FarmCrop::getCrop).toList();
    }
}
