package com.chemi.lab.gps;


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
public class Gps extends PriKey  implements Serializable, GenericEntity<Gps> {
    private String latitude;
    private String longitude;
    private String date;
    private String time;

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