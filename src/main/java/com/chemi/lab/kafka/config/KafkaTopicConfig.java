package com.chemi.lab.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic contactUsNotificationTopic(){
        return TopicBuilder.name("contact_us").build();
    }
    @Bean
    public NewTopic emailVerifyUsNotificationTopic(){
        return TopicBuilder.name("verify_email").partitions(2).build();
    }
    @Bean
    public NewTopic smsNotificationTopic(){
        return TopicBuilder.name("sms-notification").build();
    }
    @Bean
    public NewTopic smsOTPTopic(){
        return TopicBuilder.name("sms-otp").build();
    }
}
