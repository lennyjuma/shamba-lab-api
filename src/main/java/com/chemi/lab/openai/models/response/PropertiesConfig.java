package com.chemi.lab.openai.models.response;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PropertiesConfig {
    private String status;

    public PropertiesConfig(String name) {
        this.name = name;
    }

    private String name;
    private String importance;
    private String recommendation;


    public PropertiesConfig(PropertiesConfig propertiesConfig) {
        this.status = propertiesConfig.getStatus();
        this.name = propertiesConfig.getName();
        this.importance = propertiesConfig.getImportance();
        this.recommendation = propertiesConfig.getRecommendation();
    }
}
