package com.chemi.lab.mkulima.farm;

import com.chemi.lab.auth.models.Customer;
import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.mkulima.crop.Crop;
import com.chemi.lab.utils.PriKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shamba extends PriKey   implements Serializable, GenericEntity<Shamba> {
    private String name;
    @Enumerated(EnumType.STRING)
    private FarmType farmingType;
    private String location;

    @OneToMany(mappedBy = "shamba")
    private List<Crop> crop;

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
