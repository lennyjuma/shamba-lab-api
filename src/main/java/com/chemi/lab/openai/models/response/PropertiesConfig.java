package com.chemi.lab.openai.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesConfig {
    private String status;
    private String importance;
    private String recommendation;
}
