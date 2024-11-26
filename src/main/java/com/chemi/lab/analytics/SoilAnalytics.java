package com.chemi.lab.analytics;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SoilAnalytics {
    private Charts nitrogen = new Charts();
    private Charts potassium = new Charts();
    private Charts phosphorus = new Charts();
    private Charts conductivity = new Charts();
    private Charts moisture = new Charts();
    private Charts temperature = new Charts();
    private Charts pH = new Charts();
    private List<String> categories = new ArrayList<>();
    private List<String> crops = new ArrayList<>();

}
