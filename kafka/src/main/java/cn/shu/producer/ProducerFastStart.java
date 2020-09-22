package cn.shu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerFastStart {
    public static final String brokerList ="180.76.166.199:9092";
    public static final String topic ="topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers",brokerList);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        //配置生产者客户端参数并创建Kafkaproducer实例
        KafkaProducer<String,String> producer= new KafkaProducer<>(properties);
        //构建所需要发送的消息
        ProducerRecord<String,String> record=new ProducerRecord<>(topic,"hello,kafka");
        //发送消息
        producer.send(record);

        producer.close();

    }
}
