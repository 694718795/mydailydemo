package cn.shu.kafkautil.kafka;

import cn.shu.kafkautil.init.TopicConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-09-22 10:30
 */
public class Consumer {



    @KafkaListener(topics = {TopicConstant.Test1, TopicConstant.Test2}, groupId = "group1", containerFactory = "batchFactory")
    public void listen(List<ConsumerRecord<String, String>> recordList) {

        List<String> messages = new ArrayList<>();
        for (ConsumerRecord<String, String> record : recordList) {
            Optional<String> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                String message = kafkaMessage.get();
                if (StringUtils.isNotEmpty(message)) {

                }
            }
        }
    }
}
