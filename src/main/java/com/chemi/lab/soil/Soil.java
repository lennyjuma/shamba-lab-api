package com.chemi.lab.soil;

import com.chemi.lab.utils.PriKey;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Soil extends PriKey {
    private String nitrogen;
    private String phosphorous;
    private String potassium;
    private String conductivity;
    private String moisture;
    private String temperature;
    private String pH;
}
