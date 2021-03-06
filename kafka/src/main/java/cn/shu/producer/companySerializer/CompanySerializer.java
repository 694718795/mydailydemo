package cn.shu.producer.companySerializer;

import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @description: 自定义序列化器
 * @author: shurunlong
 * @create: 2019-12-09 16:17
 */
public class CompanySerializer implements Serializer<Company> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Company company) {
        if(company==null){
            return null;
        }
        byte[] name,address;

        try {
            if(company.getName()!=null){
                name=company.getName().getBytes("UTF-8");
            }else {
                name=new byte[0];
            }
            if(company.getName()!=null){
                address=company.getAddress().getBytes("UTF-8");
            }else {
                address=new byte[0];
            }
            ByteBuffer buffer =ByteBuffer.allocate(4+4+name.length+address.length);
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);
            return buffer.array();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }

    @Override
    public void close() {

    }
}
