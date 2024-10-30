package com.chemi.lab.mkulima.composite;

import com.chemi.lab.mkulima.crop.Crop;
import com.chemi.lab.mkulima.farm.Shamba;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FarmCrop {
    @JsonIgnore
    @EmbeddedId
    private FarmCropEmbeddable id;

    @JsonIgnore
    @ManyToOne()
    @MapsId("shambaId")
    @JoinColumn(name = "shamba_id")
    private Shamba shamba;


//    @JsonIgnore
    @ManyToOne
    @MapsId("cropId")
    @JoinColumn(name = "crop_id")
    private Crop crop;
}
