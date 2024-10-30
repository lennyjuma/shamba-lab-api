package com.chemi.lab.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirAnalytics {
    private Charts temp = new Charts();
    private Charts humidity = new Charts();
    private List<String> categories = new ArrayList<>();
    private List<String> crops = new ArrayList<>();

}
