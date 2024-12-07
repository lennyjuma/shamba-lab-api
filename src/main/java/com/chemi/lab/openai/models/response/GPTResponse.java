package com.chemi.lab.openai.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GPTResponse {
private String crop;
private SoilProperties soilProperties;
private String summary = "make this at most 300 letters";
//private SummaryConfig summary;
private FertilizerRecommendation fertilizer_recommendation;
}
