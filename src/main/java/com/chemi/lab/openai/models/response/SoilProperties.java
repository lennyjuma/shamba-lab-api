package com.chemi.lab.openai.models.response;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SoilProperties {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "status", column = @Column(name = "nitrogen_status")),
            @AttributeOverride( name = "recommendation", column = @Column(name = "nitrogen_recommendation")),
            @AttributeOverride( name = "importance", column = @Column(name = "nitrogen_importance")),
            @AttributeOverride( name = "name", column = @Column(name = "nitrogen_name"))
    })
    private PropertiesConfig nitrogen = new PropertiesConfig("nitrogen");
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "status", column = @Column(name = "phosphorus_status")),
            @AttributeOverride( name = "recommendation", column = @Column(name = "phosphorus_recommendation")),
            @AttributeOverride( name = "importance", column = @Column(name = "phosphorus_importance")),
            @AttributeOverride( name = "name", column = @Column(name = "phosphorus_name"))
    })
    private PropertiesConfig phosphorus = new PropertiesConfig("phosphorus");
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "status", column = @Column(name = "potassium_status")),
            @AttributeOverride( name = "recommendation", column = @Column(name = "potassium_recommendation")),
            @AttributeOverride( name = "importance", column = @Column(name = "potassium_importance")),
            @AttributeOverride( name = "name", column = @Column(name = "potassium_name"))
    })
    private PropertiesConfig potassium = new PropertiesConfig("potassium");
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "status", column = @Column(name = "conductivity_status")),
            @AttributeOverride( name = "recommendation", column = @Column(name = "conductivity_recommendation")),
            @AttributeOverride( name = "importance", column = @Column(name = "conductivity_importance")),
            @AttributeOverride( name = "name", column = @Column(name = "conductivity_name"))
    })
    private PropertiesConfig conductivity = new PropertiesConfig("conductivity");
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "status", column = @Column(name = "moisture_status")),
            @AttributeOverride( name = "recommendation", column = @Column(name = "moisture_recommendation")),
            @AttributeOverride( name = "importance", column = @Column(name = "moisture_importance")),
            @AttributeOverride( name = "name", column = @Column(name = "moisture_name"))
    })
    private PropertiesConfig moisture = new PropertiesConfig("moisture");
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "status", column = @Column(name = "temperature_status")),
            @AttributeOverride( name = "recommendation", column = @Column(name = "temperature_recommendation")),
            @AttributeOverride( name = "importance", column = @Column(name = "temperature_importance")),
            @AttributeOverride( name = "name", column = @Column(name = "temperature_name"))
    })
    private PropertiesConfig temperature = new PropertiesConfig("temperature");
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "status", column = @Column(name = "pH_status")),
            @AttributeOverride( name = "recommendation", column = @Column(name = "pH_recommendation")),
            @AttributeOverride( name = "importance", column = @Column(name = "pH_importance")),
            @AttributeOverride( name = "name", column = @Column(name = "pH_name"))
    })
    private PropertiesConfig pH = new PropertiesConfig("pH");
}
