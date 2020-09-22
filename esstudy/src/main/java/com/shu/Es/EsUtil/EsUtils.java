package com.shu.Es.EsUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.basp.uniformdb.HttpMethod;

import com.shu.Es.EsConfig.ESConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Collections;
import java.util.HashMap;

@Component
@Slf4j
public class EsUtils {

    @Autowired
    private ESConfig esConfig;

    /**
     * 判断index是否存在
     * @param indexName
     */
    public boolean checkIndexExist(String indexName) {
        boolean exist = false;
        if (StringUtils.isNotBlank(indexName)) {
            try {
                GetRequest request = new GetRequest(indexName);
                exist = esConfig.getEsRawClient().exists(request);

            } catch (IOException e) {
                log.error("IOException:{}", e);
            }
        }
        return exist;
    }

    /**
     * 查看api信息
     */
    public JSONObject getApi() {
        String result = null;
        try {
            String endpoint = "/_cat";
            Response response = esConfig.getEsRawClient().performRequest(HttpMethod.GET, endpoint);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error("ApiException:{}", e);
        }
        return JSONObject.parseObject(result);
    }

    /**
     * 删除索引
     */
    public @ResponseBody String deleteIndex(String indexName) {
        boolean deleteSign = true;
        DeleteResponse response = null;
        try {
            DeleteRequest deleteRequest = new DeleteRequest(indexName);
            response = esConfig.getEsRawClient().delete(deleteRequest);
        } catch (Exception e) {
            log.error("DETELEException:{}", e);
            deleteSign = false;
        }
        return deleteSign ? "删除成功:{}" + response.toString() : "删除失败";
    }


    /**
     * 创建索引，且指定分片和副本数
     * @param indexName 索引
     * @param shards 分片数
     * @param replicas 副本数
     * @return
     */
    public String createIndex(String indexName, String shards,String replicas) {
        String result = null;
        try {
            String endpoint = "/" + indexName;
            HashMap<String, String> pushMap = new HashMap<>();
            pushMap.put("number_of_shards",shards);
            pushMap.put("number_of_replicas",replicas);
//            HashMap<String, String> body = new HashMap<>();
//            body.put("settings", JSON.toJSONString(pushMap));
            String body = "{\"settings\" : "+ JSON.toJSONString(pushMap)+"}";
            Response response = esConfig.getEsRawClient().performRequest(HttpMethod.PUT, endpoint,new HashMap<>(),body);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            log.error("Exception:{}", e);
        }
        return result;
    }


    /**
     * 创建索引，且指定分片和副本数
     * @param indexName 索引
     * @return
     */
    public String createmapping(String indexName,JSONObject jsonObject) {
        String result = null;
        try {
            String endpoint = "/" + indexName;


            Response response = esConfig.getEsRawClient().performRequest(HttpMethod.PUT, endpoint,new HashMap<>(),jsonObject.toJSONString());
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            log.error("Exception:{}", e);
        }
        return result;
    }

    /**
     * 修改索引的副本数
     * @param index 索引
     * @param replicas 副本数
     * @return
     */
    public String editIndexReplicasNumber(String index,String replicas) {
        String result = null;
        try {
            String endpoint = "/" + index +"/_settings";
            //index.number_of_replicas:副本数
            Response response = esConfig.getEsRawClient().performRequest(HttpMethod.PUT, endpoint,new HashMap<>(), JSON.toJSONString(Collections.singletonMap("number_of_replicas", replicas)));
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            log.error("Exception:{}", e);
        }
        return result;
    }


    /**
     * 根据sql语句获取es的string类型的搜索结果
     *
     * @param searchSql
     */
    public JSONObject getEsResultBySearchSql(String searchSql) {
        String result = null;
        if (StringUtils.isBlank(searchSql)) {
            return null;
        }
        log.info("search es sql:{}", searchSql);
        try {
            long startTime = System.currentTimeMillis();
            result = esConfig.getEsRawClient().executeEsql(searchSql);
            log.info("es Sql time is:{} and sql is:{}", System.currentTimeMillis() - startTime, searchSql);
        } catch (ConnectException e) {
            log.error("ConnectException:{}", e);
            throw new RuntimeException("ConnectException");
        } catch (ResponseException e) {
            log.error("ResponseException:{}", e);
            if (e.toString().indexOf("index_closed_exception") != -1) {
                //说明索引有是close的状态，
                if (e.toString().indexOf("noahadvancetotal") != -1 || e.toString().indexOf("noahhivefacets") != -1
                        || e.toString().indexOf("noahhivefits") != -1 || e.toString().indexOf("noahhivetemp") != -1) {
                    //说明是高级模式的表不存在
                    return null;
                }
                throw new RuntimeException("ResponseException");
            }
        } catch (Exception e) {
            log.error("search exception es sql:{}", searchSql);
            throw new RuntimeException("");
        }
        log.info("search es sql result:{}", result);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return JSON.parseObject(result);
    }

    /**
     * 根据sql语句获取es的string类型的搜索结果
     */
    public JSONObject getIndexDataByIndexNameAndType(String indexName, String type) {
        String result = null;
        String endPoint="";
        if (StringUtils.isBlank(indexName)) {
            return null;
        }else if(StringUtils.isNotBlank(type)){
            endPoint="/"+indexName+"/"+type+"/_search";
        }else{
            endPoint="/"+indexName+"/_search";
        }
        log.info("endPoint:{}", endPoint);
        try {
            long startTime = System.currentTimeMillis();
            Response response = esConfig.getEsRawClient().performRequest(HttpMethod.GET ,endPoint);
            result = EntityUtils.toString(response.getEntity());
            log.info("es Sql time is:{} and sql is:{}", System.currentTimeMillis() - startTime, endPoint);
        } catch (ConnectException e) {
            log.error("ConnectException:{}", e);
            throw new RuntimeException("ConnectException");
        } catch (ResponseException e) {
            log.error("ResponseException:{}", e);
            if (e.toString().indexOf("index_closed_exception") != -1) {
                //说明索引有是close的状态，
                if (e.toString().indexOf("noahadvancetotal") != -1 || e.toString().indexOf("noahhivefacets") != -1
                        || e.toString().indexOf("noahhivefits") != -1 || e.toString().indexOf("noahhivetemp") != -1) {
                    //说明是高级模式的表不存在
                    return null;
                }
                throw new RuntimeException("ResponseException");
            }
        } catch (Exception e) {
        }
        log.info("search es sql result:{}", result);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        return JSON.parseObject(result);
    }


    /**
     * 查看es集群是否搭建成功
     */
    public JSONObject getClusterStatus() {
        String result = null;
        try {
            Response response = esConfig.getEsRawClient().performRequest(HttpMethod.GET,"/", Collections.singletonMap("pretty", "true"));
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error("ApiException:{}", e);
        }
        return JSONObject.parseObject(result);
    }


    /**
     *
     * @param bulkRequest 批量存入Es中的数据
     * @return
     */
   public String saveEsDateByBulkResponse(BulkRequest bulkRequest) {
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = esConfig.getEsRawClient().bulk(bulkRequest);
        } catch (IOException e) {
            log.error("save es failer:{}", JSONObject.toJSONString(bulkResponse.buildFailureMessage()));
            throw new RuntimeException("IOException e:"+ JSONObject.toJSONString(bulkResponse.buildFailureMessage()));
        }
        if (bulkResponse.hasFailures()) {
            log.error("save es failer:{}", JSONObject.toJSONString(bulkResponse.buildFailureMessage()));
        }
        return  JSONObject.toJSONString(bulkResponse.buildFailureMessage());

    }


    /**
     *
     * @param data 需要存入到Es数据
     * @param index 数据存入的index
     * @return
     */
    public String saveDataToIndex(JSONObject data,String index,String type){
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.add(new IndexRequest(index, type).source(data, XContentType.JSON));
       return saveEsDateByBulkResponse(bulkRequest);
    }

}
