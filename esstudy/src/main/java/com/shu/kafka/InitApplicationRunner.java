package com.shu.kafka;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(value = 1)
public class InitApplicationRunner implements ApplicationRunner {
	@Autowired
	private KafkaHelper kafkaHelper;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		kafkaHelper.createTopic(TopicConstant.Test1);

	}

}
