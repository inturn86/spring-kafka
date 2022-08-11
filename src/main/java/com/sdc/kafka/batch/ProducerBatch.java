package com.sdc.kafka.batch;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProducerBatch {

	private final KafkaTemplate<String, String> kafkaTemplate;

	private static String TOPIC_NAME = "dev-topic";

	@Scheduled(initialDelay = 1000, fixedDelay = 1000)
	public void eventCreate() {

		String message = "test kafka1";

		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC_NAME, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				// TODO Auto-generated method stub
	            System.out.println("Sent message=[" + message +
	                      "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
	            System.out.println("Unable to send message=["
	                    + message + "] due to : " + ex.getMessage());
			}
		});

	}
}
