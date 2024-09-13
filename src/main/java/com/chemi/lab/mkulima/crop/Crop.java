package com.chemi.lab.mkulima.crop;

import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.mkulima.farm.Shamba;
import com.chemi.lab.utils.PriKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Crop extends PriKey   implements Serializable, GenericEntity<Crop> {
    private String name;

    @JsonIgnore
    @ManyToOne
    private Shamba shamba;

    @Override
    public void update(Crop source) {
        setName(source.getName());
    }

    @Override
    public Crop createNewInstance() {
        Crop crop = new Crop();
        crop.update(this);
        return crop;
    }
}
