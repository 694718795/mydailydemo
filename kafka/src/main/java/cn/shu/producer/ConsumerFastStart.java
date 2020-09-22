package cn.shu.producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @description: 消费者
 * @author: shurunlong
 * @create: 2019-12-06 16:12
 */
public class ConsumerFastStart {
    public static final String brokerList ="180.76.166.199:9092";
    public static final String topic ="topic-demo";
    public static final String groupId ="group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers",brokerList);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        //设置消费组的名称
        properties.put("group.id",groupId);
        //创建消费者客户端实例
        KafkaConsumer<String,String> consumer=new KafkaConsumer<>(properties);
        //订阅主题
        consumer.subscribe(Collections.singleton(topic));
        //循环消费消息
        while (true){
            ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }
    }

}
