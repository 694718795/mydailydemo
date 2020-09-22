package com.shu.Es.Controller;

import com.alibaba.fastjson.JSONObject;

import com.shu.Es.EsUtil.EsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private EsUtils esUtils;

    @GetMapping(value = "/check/index")
    public boolean checkIndexByName(String indexName) {
        return esUtils.checkIndexExist(indexName);
    }

    @GetMapping(value = "/api")
    public JSONObject getApi() {
        return esUtils.getApi();
    }

    @GetMapping(value = "/creat/index")
    public String creatIndex(String indexName,String shards,String replicas ) {
        return esUtils.createIndex(indexName,shards,replicas);
    }

    @PutMapping(value = "/creat/mapping")
    public String mapping(@RequestParam String indexName,@RequestBody JSONObject jsonObject ) {
        return esUtils.createmapping(indexName,jsonObject);
    }

    @GetMapping(value = "/sql")
    public JSONObject getResultBySql(String searchSql) {
        return esUtils.getEsResultBySearchSql(searchSql);
    }

    @GetMapping(value = "/delete")
    public String deleteIndexBySIndexName(String indexName) {
        return esUtils.deleteIndex(indexName);
    }

    @GetMapping(value = "/cluster/status")
    public JSONObject getClusterStatus() {
        return esUtils.getClusterStatus();
    }

    @GetMapping(value = "/index/status")
    public String editReplicas(String indexName,String replicas ) {
        return esUtils.editIndexReplicasNumber(indexName, replicas);
    }

    @PostMapping(value = "/save")
    public String saveDataToEs(@RequestParam(name = "indexName") String indexName,
                                        @RequestBody JSONObject data,@RequestParam String type) {
        return esUtils.saveDataToIndex(data,indexName,type);
    }

    @GetMapping(value = "/index/data")
    public JSONObject getIndexData(@RequestParam(name = "indexName") String indexName,
                                        @RequestParam(name = "type", required = false) String type) {
        return esUtils.getIndexDataByIndexNameAndType(indexName,type);
    }

}
