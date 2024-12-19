package com.chemi.lab.openai.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GPTResponseDTO {
    private String crop;
    private List<PropertiesConfig> soilProperties;
    private String summary ;
    //private SummaryConfig summary;
    private FertilizerRecommendation fertilizer_recommendation;

    public GPTResponseDTO(GPTResponse gptResponseDTO, List<PropertiesConfig> soilProperties) {
        this.crop = gptResponseDTO.getCrop();
        this.soilProperties = soilProperties;
        this.summary = gptResponseDTO.getSummary();
        this.fertilizer_recommendation = gptResponseDTO.getFertilizer_recommendation();
    }
}
