package com.shu.aopimplement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * 参考博客:  https://blog.csdn.net/OrangeJack/article/details/82913804
 *
 * @description:
 * @author: shurunlong
 * @create: 2020-06-08 10:04
 */
@Component
public class AESEncrypt {


    public static String encrypt(String data, String key) {

        String ivString = "0000000000000000";
        //偏移量
        byte[] iv = ivString.getBytes();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int length = dataBytes.length;
            //计算需填充长度
            if (length % blockSize != 0) {
                length = length + (blockSize - (length % blockSize));
            }
            byte[] plaintext = new byte[length];
            //填充
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            //设置偏移量参数
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encryped = cipher.doFinal(plaintext);

            return parseByte2HexStr(encryped);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String desEncrypt(String data, String key) {

        String ivString = "0000000000000000";
        byte[] iv = ivString.getBytes();

        try {
            byte[] encryp = parseHexStr2Byte(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(encryp);
            return new String(original);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for (int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte) (high * 16 + low);
            }

            return result;
        }
    }

    public static void main(String[] args) {
        String data = "123456";
        String key = "186751244B391A6DCA84778E0D6A8910";
        String encrypt = encrypt(data, key);
        System.out.println("加密前：" + data);
        System.out.println("加密后：" + encrypt);
        String desEncrypt = desEncrypt(encrypt, key);
        System.out.println("解密后：" + desEncrypt);
    }



}

