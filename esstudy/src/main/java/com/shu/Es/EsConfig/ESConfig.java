package com.shu.Es.EsConfig;

import com.alibaba.fastjson.JSONObject;
import com.basp.uniformdb.DbType;
import com.basp.uniformdb.EsProps;
import com.basp.uniformdb.EsRawClient;
import com.basp.uniformdb.HttpMethod;
import com.shu.Es.pojo.EsIndexState;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动的时候加载一些系统参数或者检索健康状态，就要用到ApplicationRunner或者CommonLinerRunner
 * 当项目启动时，run()方法便会自动执行
 * 判断Es中index的健康状态,
 * 获取Es客户端:EsRawClient
 */

@Component
@Data
@Slf4j
public class ESConfig implements ApplicationRunner {


    private EsRawClient esRawClient = null;

    private String esNode;

    @Value("${es.nodes}")
    private String esnodes;

    //默认5分钟
    @Value("${search.es.timeout:60000}")
    private int esTimeout;

    @Autowired
    private RedisClient redisClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String[] esnode = esnodes.split(",");
        String[] host = new String[esnode.length];
        for (int i = 0; i < esnode.length; i++) {
            host[i] = esnode[i].split(":")[1] + ":9200";
            esNode = host[i];
        }
        log.info("esnodes:{}", host);
        EsProps esProps = new EsProps(DbType.ES, 50, 30,
                builder -> builder.setConnectionRequestTimeout(5000).setSocketTimeout(esTimeout).setConnectionRequestTimeout(5000), host)
                .setMaxRetryTimeoutMillis(esTimeout);
        esRawClient = new EsRawClient(esProps);
        Response response = null;
        //判断Es节点转态
        List<EsIndexState> indexStateListRedis = new ArrayList<>();
        try {
            response = this.esRawClient.performRequest(HttpMethod.GET, "/_cat/indices?format=json&pretty&h=status,index,health");
            HttpEntity entity = response.getEntity();
            String indexIndicesString = entity == null ? "" : EntityUtils.toString(entity);
            List<EsIndexState> indexStateList = JSONObject.parseArray(indexIndicesString, EsIndexState.class);
            for (EsIndexState esIndexState : indexStateList) {
                if ("close".equals(esIndexState.getStatus())) {
                    //说明是关闭状态的索引
                    indexStateListRedis.add(esIndexState);
                    continue;
                }
                if ("green".equals(esIndexState.getHealth())) {
                    indexStateListRedis.add(esIndexState);
                } else if ("yellow".equals(esIndexState.getHealth())) {
                    indexStateListRedis.add(esIndexState);
                } else if ("red".equals(esIndexState.getHealth())) {
                    //说明不允许进行搜索，
                    log.error("es index is red :{}", esIndexState.getIndex());
                    indexStateListRedis.clear();
                    break;
                }
            }
        } catch (Exception e) {
            indexStateListRedis.clear();
            log.error("index es error is {}", e);
        }
//        redisClient.set("es_index_key", JSONObject.toJSONString(indexStateListRedis));
    }


}
