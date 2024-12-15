package com.chemi.lab.openai.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SummaryConfig {
    private String nitrogen;
    private String phosphorus;
    private String potassium;
    private String pH;
    private String recommended_fertilizer;
    private String recommendation_during_planting;
    private String recommendation_during_top_dressing;
    private String recommendation_after_harvest;
}
