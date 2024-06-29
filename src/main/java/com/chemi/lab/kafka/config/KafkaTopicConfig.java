package com.chemi.lab.kafka.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic startSTKFromMQTTTopic(){
        return TopicBuilder.name("start_stk_from_mqtt").build();
    }
    @Bean
    public NewTopic ackSTKTopic(){
        return TopicBuilder.name("ack_stk").build();
    }@Bean
    public NewTopic ackSTKToMqttTopic(){
        return TopicBuilder.name("ack_stk_mqtt").build();
    }
    @Bean
    public NewTopic resultSTKTopic(){
        return TopicBuilder.name("result_stk").build();
    }
    @Bean
    public NewTopic resultSTKToMqttTopic(){
        return TopicBuilder.name("result_stk_mqtt").build();
    }
    @Bean
    public NewTopic reversalPayloadFromChemichemi(){
        return TopicBuilder.name("reversal_payload_chemichemi").build();
    }
    @Bean
    public NewTopic lennyTopic(){
        return TopicBuilder.name("test").build();
    }
}
