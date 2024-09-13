package com.chemi.lab.mkulima.crop;

import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.utils.PriKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Crop extends PriKey   implements Serializable, GenericEntity<Crop> {
    private String name;

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
