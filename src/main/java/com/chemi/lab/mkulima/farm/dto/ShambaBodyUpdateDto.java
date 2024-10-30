package com.chemi.lab.mkulima.farm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShambaBodyUpdateDto {
    private String name;
    private String farmingType;
    private String location;
    private List<String> addedCrops;
    private List<String> removedCrops;
}
