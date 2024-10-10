package com.chemi.lab.mkulima.crop;

import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.mkulima.composite.FarmCrop;
import com.chemi.lab.mkulima.farm.Shamba;
import com.chemi.lab.utils.PriKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Crop extends PriKey   implements Serializable, GenericEntity<Crop> {
    @NotNull
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "crop")
    private Set<FarmCrop> farmCrops = new HashSet<>();



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
    public static Crop CreateCrop(String name) {
        Crop crop = new Crop();
        crop.setName(name);
        return crop;
    }
}
