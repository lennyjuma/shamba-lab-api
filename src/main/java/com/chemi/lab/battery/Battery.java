package com.chemi.lab.battery;

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
public class Battery extends PriKey {
    private String voltage;
    private String current;
    private String power;
    private String state;
}
