
package cn.shu.kafkautil.kafka;


import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import lombok.extern.log4j.Log4j;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Log4j
public class KafkaHelper {
    private String bmZkNodes = "180.76.166.199";


    public void createTopic(String topicName) {
        String node = bmZkNodes;
        boolean success = false;

        ZkClient zkClient = null;
        ZkUtils zkUtils = null;
        try {
            String zkIp =bmZkNodes;
            zkClient = new ZkClient(zkIp, 30000, 30000, ZKStringSerializer$.MODULE$);
            zkUtils = new ZkUtils(zkClient, new ZkConnection(zkIp), false);
            if (!AdminUtils.topicExists(zkUtils, topicName)) {
                //partition  分片    replicationFactor
                AdminUtils.createTopic(zkUtils, topicName, 2, 1, new Properties(),
                        RackAwareMode.Disabled$.MODULE$);
            }
            success = true;
            log.error("在" + node + "节点创建名为" + topicName + "的topic成功");
            System.out.println("在" + node + "节点创建名为" + topicName + "的topic成功");
        } catch (Exception e) {

            log.error("在" + node + "节点创建名为" + topicName + "的topic失败", e);
            System.out.println("在" + node + "节点创建名为" + topicName + "的topic失败" + e);
        } finally {
            if (zkClient != null) {
                try {
                    zkClient.close();
                } catch (Exception e) {
                    log.error("zkClient close fail ", e);
                    System.out.println("zkClient close fail " + e);
                }
            }
            if (zkUtils != null) {
                try {
                    zkUtils.close();
                } catch (Exception e) {
                    log.error("zkUtils close fail ", e);
                    System.out.println("zkUtils close fail " + e);
                }
            }
        }


        if (!success) {
            System.out.println("失败");
        }
    }


    public void createTopic1(String topicName) {
        String[] nodes = bmZkNodes.split(",");
        boolean success = false;
        for (String node : nodes) {
            ZkClient zkClient = null;
            ZkUtils zkUtils = null;
            try {
                String zkIp = node.split(":")[1];
                zkClient = new ZkClient(zkIp, 30000, 30000, ZKStringSerializer$.MODULE$);
                zkUtils = new ZkUtils(zkClient, new ZkConnection(zkIp), false);
                if (!AdminUtils.topicExists(zkUtils, topicName)) {
                    AdminUtils.createTopic(zkUtils, topicName, 5, 2, new Properties(),
                            RackAwareMode.Disabled$.MODULE$);
                }
                success = true;
                log.error("在" + node + "节点创建名为" + topicName + "的topic成功");
                System.out.println("在" + node + "节点创建名为" + topicName + "的topic成功");
            } catch (Exception e) {

                log.error("在" + node + "节点创建名为" + topicName + "的topic失败", e);
                System.out.println("在" + node + "节点创建名为" + topicName + "的topic失败" + e);
            } finally {
                if (zkClient != null) {
                    try {
                        zkClient.close();
                    } catch (Exception e) {
                        log.error("zkClient close fail ", e);
                        System.out.println("zkClient close fail " + e);
                    }
                }
                if (zkUtils != null) {
                    try {
                        zkUtils.close();
                    } catch (Exception e) {
                        log.error("zkUtils close fail ", e);
                        System.out.println("zkUtils close fail " + e);
                    }
                }
            }
            if (success) {
                break;
            }
        }
        if (!success) {
            System.out.println("失败");
        }
    }
}
