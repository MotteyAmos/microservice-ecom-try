package com.ecom2.payment_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaPaymentTopicConfi {

    @Bean
    public NewTopic paymentTopic(){
        return TopicBuilder
                .name("payment-topic")
                .build();
    }
}
