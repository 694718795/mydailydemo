package com.shu.declareImplment;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-21 11:48
 */
@IpListRule(id = "id", ip = "ip", netDom = "netDom", message = "ip错误")
@Data
public class AssetInfo implements Serializable {
    private  Long id;
    private  String ip;
    private  String netDom;
}
