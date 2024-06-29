package com.chemi.lab.gps;


import com.chemi.lab.utils.PriKey;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gps extends PriKey {
    private String latitude;
    private String longitude;
    private String date;
    private String time;
}
