package com.chemi.lab.mkulima.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class FarmCropEmbeddable {
    @Column(name = "shamba_id")
    private String shambaId;
    @Column(name = "crop_id")
    private String cropId;
}
