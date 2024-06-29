package com.chemi.lab.farm;


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
public class Farm extends PriKey {
    private String crop;
    private String phone;
    private String deviceID;
}
