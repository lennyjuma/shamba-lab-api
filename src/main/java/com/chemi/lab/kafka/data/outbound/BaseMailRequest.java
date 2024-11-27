package com.chemi.lab.kafka.data.outbound;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseMailRequest {
    private String from;
    private String to;
    private String subject;
    private String message;
}
