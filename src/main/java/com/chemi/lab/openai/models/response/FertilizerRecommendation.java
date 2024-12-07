package com.chemi.lab.openai.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FertilizerRecommendation {
    private String ideal_type_of_fertilizer;
    private String suggested_nutrient_ratios;
    private String application_rate;
    private String application_method; //Granular, liquid, foliar sprays, or fertigation systems might be recommended based on soil type and crop needs.
    private String supplementary_practices;
    private String importance_of_recommended ;

//todp pass type of crop and growth stage
}
