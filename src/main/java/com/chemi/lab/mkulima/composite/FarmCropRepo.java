package com.chemi.lab.mkulima.composite;

import com.chemi.lab.mkulima.crop.CropRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FarmCropRepo extends JpaRepository<FarmCrop,FarmCropEmbeddable> {
}
