package com.sdc.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

	@Bean
	public NewTopic setDevTopic() {
		return new NewTopic("dev-topic", 3, (short) 1);
	}

}
