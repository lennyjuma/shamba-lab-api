package com.chemi.lab.openai.models.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SoilProperties {

    private PropertiesConfig nitrogen;
    private PropertiesConfig phosphorus;
    private PropertiesConfig potassium;
    private PropertiesConfig conductivity;
    private PropertiesConfig moisture;
    private PropertiesConfig temperature;
    private PropertiesConfig pH;
}
