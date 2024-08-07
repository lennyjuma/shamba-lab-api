package com.chemi.lab.battery;

import com.chemi.lab.air.Air;
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
public class Battery extends PriKey implements Serializable, GenericEntity<Battery> {
    private String voltage;
    private String current;
    private String power;
    private String state;
    private String deviceId;

    @Override
    public void update(Battery source) {
        setVoltage(source.getVoltage());
        setCurrent(source.getCurrent());
        setPower(source.getPower());
        setPower(source.getPower());
    }

    @Override
    public Battery createNewInstance() {
        Battery battery = new Battery();
        battery.update(this);
        return battery;
    }
}
