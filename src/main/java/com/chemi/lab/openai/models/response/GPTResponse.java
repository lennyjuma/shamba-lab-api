package com.chemi.lab.openai.models.response;

import com.chemi.lab.utils.PriKey;
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
@Entity
public class GPTResponse {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String soilReadingId;
    private String crop;
    @Embedded
    private SoilProperties soilProperties;

    @Column(length = 65535, columnDefinition = "TEXT") // Increase size
    private String summary ;
    //private SummaryConfig summary;
    @Embedded
    private FertilizerRecommendation fertilizer_recommendation;


    public GPTResponse(String soilReadingId) {
        this.soilReadingId = soilReadingId;
    }


}
