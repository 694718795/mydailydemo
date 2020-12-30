package org.apache.kafka.connect.file;

import org.apache.kafka.connect.cli.ConnectDistributed;
import org.apache.kafka.connect.cli.ConnectStandalone;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 文件上传
 * @author: shurunlong
 * @create: 2020-12-04 09:51
 */
public class FileSourceTest {


     //kafka ->file
    @Test
    public void testKafkaConnectfile() {

        consumeViaKafkaConnectTofile();
    }

     //file->kafka
    @Test
    public void testfileConnectKafka() {

        try {
            consumeFileConnectToKafka();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consumeViaKafkaConnectTofile() {
        List<String> params = new ArrayList<String>();
        params.add(resource("connect-standalone.properties").getPath());
        params.add(resource("connect-file-sink-test.properties").getPath());

        try {
            ConnectStandalone.main(params.toArray(new String[params.size()]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private URL resource(String name) {
        return Thread.currentThread().getContextClassLoader().getResource(name);
    }


    private void consumeFileConnectToKafka() {
        List<String> params = new ArrayList<String>();
        params.add(resource("connect-standalone.properties").getPath());
        params.add(resource("connect-file-source.properties").getPath());


        try {
            ConnectStandalone.main(params.toArray(new String[params.size()]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
