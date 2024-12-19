package com.chemi.lab.openai.models.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GPTResponseStructure {

    private String crop;
    @Embedded
    private SoilProperties soilProperties;

    @Column(length = 65535, columnDefinition = "TEXT") // Increase size
    private String summary ;
    //private SummaryConfig summary;
    @Embedded
    private FertilizerRecommendation fertilizer_recommendation;

    public  GPTResponse  returnGPTResponse () {

        GPTResponse gptResponse = new GPTResponse();
        gptResponse.setCrop(this.crop);
        gptResponse.setSoilProperties(this.soilProperties);
        gptResponse.setSummary(this.summary);
        gptResponse.setFertilizer_recommendation(this.fertilizer_recommendation);

        return gptResponse;
    }
}
