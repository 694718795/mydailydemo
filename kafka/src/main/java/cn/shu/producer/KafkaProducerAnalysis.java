package cn.shu.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @description: 2-1 生产者客户端实例代码
 * @author: shurunlong
 * @create: 2019-12-09 15:28
 */
public class KafkaProducerAnalysis {
    public static final String brokerList = "180.76.166.199:9092";
    public static final String topic = "topic-demo";

    public static Properties initConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerList);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        properties.put("client.id", "producer.client.id.demo");
        return properties;
    }

    public static Properties initConfigOther() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "");
        return properties;
    }

    public static void main(String[] args) {
        Properties properties = initConfig();
        //配置生产者客户端参数并创建Kafkaproducer实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        //构建所需要发送的消息
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello,kafka");
        //第一种发送:  发后即忘  fire-and-forget
        //producer.send(record);

        //2 同步发送 sync
//        try {
//            Future<RecordMetadata> future = producer.send(record);
//            RecordMetadata recordMetadata = future.get();
//            System.out.println(recordMetadata.topic());
//            System.out.println(recordMetadata.partition());
//            System.out.println(recordMetadata.offset());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        //3 异步发送方式 async
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e != null) {
                    e.printStackTrace();
                } else {
                    System.out.println(recordMetadata.topic());
                    System.out.println(recordMetadata.partition());
                    System.out.println(recordMetadata.offset());
                }
            }
        });


        producer.close();

    }

}
