package com.chemi.lab.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoilAnalytics {
    private Charts nitrogen = new Charts();
    private Charts potassium = new Charts();
    private Charts phosphorus = new Charts();
    private Charts conductivity = new Charts();
    private Charts moisture = new Charts();
    private Charts temperature = new Charts();
    private Charts pH = new Charts();
    private List<String> categories = new ArrayList<>();

}
