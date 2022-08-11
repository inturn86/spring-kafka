package com.sdc.kafka.batch;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.sdc.kafka.service.wmd.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProducerBatch {

	private final KafkaTemplate<String, UserDTO> kafkaTemplate;

	private static String TOPIC_NAME = "dev-topic";

	@Scheduled(initialDelay = 1000, fixedDelay = 1000)
	public void eventCreate() {

		String message = "test kafka1";

		UserDTO user = UserDTO.builder().userId("userID").userName("name").build();

		//Future
		// - 블로킹으로 배치 효과가 떨어져 처리량 저하
		// - 처리량이 낮아도 되는 경우에만 사용.
		kafkaTemplate.send(TOPIC_NAME, user);

//		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
//
//			@Override
//			public void onSuccess(SendResult<String, String> result) {
//				// TODO Auto-generated method stub
//				System.out.println(
//						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
//			}
//
//			@Override
//			public void onFailure(Throwable ex) {
//				// TODO Auto-generated method stub
//				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
//			}
//		});

		//callback 처리량 저하 X - 추후 확인.

	}
}
