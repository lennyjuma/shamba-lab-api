package com.chemi.lab.soil;

import com.chemi.lab.battery.Battery;
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
public class Soil extends PriKey  implements Serializable, GenericEntity<Soil> {
    private String nitrogen;
    private String phosphorous;
    private String potassium;
    private String conductivity;
    private String moisture;
    private String temperature;
    private String pH;

    @Override
    public void update(Soil source) {
        setNitrogen(source.getNitrogen());
        setPhosphorous(source.getPhosphorous());
        setPotassium(source.getPotassium());
        setConductivity(source.getConductivity());
        setMoisture(source.getMoisture());
        setTemperature(source.getTemperature());
        setPH(source.getPH());
    }

    @Override
    public Soil createNewInstance() {
        Soil soil = new Soil();
        soil.update(this);
        return soil;
    }
}