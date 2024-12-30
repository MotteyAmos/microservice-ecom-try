package com.ecom2.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic OrderTopic(){
        return TopicBuilder
                .name("order-topic")
                .build();
    }

}
