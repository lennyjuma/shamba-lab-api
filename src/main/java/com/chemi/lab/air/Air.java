package com.chemi.lab.air;

import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.utils.PriKey;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Air extends PriKey implements Serializable, GenericEntity<Air> {
    private String humidity;
    private String temperature;

    @Override
    public void update(Air source) {
        setHumidity(source.getHumidity());
        setTemperature(source.getTemperature());
    }

    @Override
    public Air createNewInstance() {
        Air air = new Air();
        air.update(this);
        return air;
    }
}
