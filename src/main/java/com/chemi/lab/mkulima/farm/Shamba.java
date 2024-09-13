package com.chemi.lab.mkulima.farm;

import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.utils.PriKey;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shamba extends PriKey   implements Serializable, GenericEntity<Shamba> {
    private String name;
    @Enumerated(EnumType.STRING)
    private FarmType farmingType;
    private String location;

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
