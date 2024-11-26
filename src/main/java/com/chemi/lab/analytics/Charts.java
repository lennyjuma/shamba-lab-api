package com.chemi.lab.analytics;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Charts {
    private String name;
    private String reading_date;
    private List<BigDecimal> data = new ArrayList<>();
}
