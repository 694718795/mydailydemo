
package com.shu.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *	生产者
 */
@Component
@Slf4j
public class KafkaSender {
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //批量发送消息方法
    public void send(String topic, List<String> data) {
    	if(CollectionUtils.isEmpty(data)) return;
    	
    	for(String str : data) {
    		this.send(topic, str);
    	}
    }
    
    //发送消息方法
    public void send(String topic, String data) {
    	if(StringUtils.isEmpty(data)) return;
    	
    	kafkaTemplate.send(topic, data);
    	log.info("kafka写入成功--topic="+topic);
    }
    
}
