package com.chemi.lab.mkulima.farm.dto;

import com.chemi.lab.mkulima.farm.FarmType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShambaBodydto {

    private String name;
    private String farmingType;
    private String location;
    private List<String> crops;
}
