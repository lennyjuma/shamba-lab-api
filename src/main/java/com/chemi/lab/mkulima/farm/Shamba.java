package com.chemi.lab.mkulima.farm;

import com.chemi.lab.air.Air;
import com.chemi.lab.auth.models.Customer;
import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.mkulima.composite.FarmCrop;
import com.chemi.lab.mkulima.crop.Crop;
import com.chemi.lab.mkulima.crop.Crop_utils;
import com.chemi.lab.shambaLab.ShambaLab;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.utils.PriKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shamba" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@NoArgsConstructor
@AllArgsConstructor
public class Shamba extends PriKey   implements Serializable, GenericEntity<Shamba> {
    @Column(unique = true, nullable = false)
    private String name;
    private String stmName;
    @Enumerated(EnumType.STRING)
    private FarmType farmingType;
    private String location;

//    @JsonIgnore
    @OneToMany(mappedBy = "shamba")
    private List<FarmCrop> farmCrops = new ArrayList<>();
    @Transient
    private List<Crop> crops = Crop_utils.getCropList(this.farmCrops);

    @JsonIgnore
    @OneToMany(mappedBy = "shamba")
    private List<Soil> soilList;

    @JsonIgnore
    @OneToMany(mappedBy = "shamba")
    private List<Air> airList;

    @JsonIgnore
    @ManyToOne
    private Customer customer;


    @Override
    public void update(Shamba source) {
        setName(source.getName());
        setFarmingType(source.getFarmingType());
        setLocation(source.getLocation());
    }

    @Override
    public Shamba createNewInstance() {
        Shamba shamba = new Shamba();
        shamba.update(this);
        return shamba;
    }
}
