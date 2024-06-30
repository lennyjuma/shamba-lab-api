package com.chemi.lab.shambaLab;

import com.chemi.lab.air.Air;
import com.chemi.lab.farm.Farm;
import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.gps.Gps;
import com.chemi.lab.soil.Soil;
import com.chemi.lab.utils.PriKey;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class ShambaLab extends PriKey implements Serializable, GenericEntity<ShambaLab> {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "air_id", referencedColumnName = "id")
    private Air air;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farm_id", referencedColumnName = "id")
    private Farm farm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gps_id", referencedColumnName = "id")
    private Gps gps;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "soil_id", referencedColumnName = "id")
    private Soil soil;

    @Override
    public void update(ShambaLab source) {
        setAir(source.getAir());
        setFarm(source.getFarm());
        setSoil(source.getSoil());
        setGps(source.getGps());
    }

    @Override
    public ShambaLab createNewInstance() {
        ShambaLab shambaLab = new ShambaLab();
        shambaLab.update(this);
        return shambaLab;
    }
}
