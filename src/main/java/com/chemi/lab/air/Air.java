package com.chemi.lab.air;

import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.shambaLab.ShambaLab;
import com.chemi.lab.utils.PriKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
    private String deviceId;
    @JsonIgnore
    @OneToOne(mappedBy = "air")
    private ShambaLab shambaLab;

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
