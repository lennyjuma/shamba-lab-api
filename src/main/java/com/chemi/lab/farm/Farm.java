package com.chemi.lab.farm;


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
public class Farm extends PriKey  implements Serializable, GenericEntity<Farm> {
    private String crop;
    private String phone;
    private String deviceID;

    @Override
    public void update(Farm source) {
        setCrop(source.getCrop());
        setPhone(source.getPhone());
        setDeviceID(source.getDeviceID());
    }

    @Override
    public Farm createNewInstance() {
        Farm farm = new Farm();
        farm.update(this);
        return farm;
    }
}
