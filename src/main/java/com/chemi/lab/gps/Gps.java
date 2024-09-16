package com.chemi.lab.gps;


import com.chemi.lab.generics.GenericEntity;
import com.chemi.lab.mkulima.farm.Shamba;
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
public class Gps extends PriKey  implements Serializable, GenericEntity<Gps> {
    private String latitude;
    private String longitude;
    private String date;
    private String time;
    private String deviceId;
    @JsonIgnore
    @OneToOne(mappedBy = "gps")
    private ShambaLab shambaLab;


    @Override
    public void update(Gps source) {
        setLatitude(source.getLatitude());
        setLongitude(source.getLongitude());
        setDate(source.getDate());
        setTime(source.getTime());
    }

    @Override
    public Gps createNewInstance() {
        Gps gps = new Gps();
        gps.update(this);
        return gps;
    }
}
