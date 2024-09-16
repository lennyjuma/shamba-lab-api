package com.chemi.lab.farm;


import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.mkulima.farm.Shamba;
import com.chemi.lab.shambaLab.ShambaLab;
import com.chemi.lab.utils.PriKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
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
    private String deviceId;


    @OneToOne(mappedBy = "farm")
    private ShambaLab shambaLab;

    @Override
    public void update(Farm source) {
        setCrop(source.getCrop());
        setPhone(source.getPhone());
        setDeviceId(source.getDeviceId());
    }

    @Override
    public Farm createNewInstance() {
        Farm farm = new Farm();
        farm.update(this);
        return farm;
    }
}
