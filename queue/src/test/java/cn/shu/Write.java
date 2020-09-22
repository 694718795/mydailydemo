package cn.shu;

import java.io.*;

public class Write {
    public static void main(String[] args) {
        long star = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            writeStr("0123456789");
        }
        long end = System.currentTimeMillis();
        System.out.println("写入完毕用时:"+(end-star));
    }


    public static void writeStr(String str){

        //定义文件路径，没有该文件会自动创建，如果路径有文件夹，一定要有，不会自动创建文件夹
//        String filename = "e:"+File.separator+"a"+File.separator+"b.txt";
        String path = "G:"+ File.separator+"javademo"+File.separator+"srl"+File.separator+"queue"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"origin";
//        String path = Write_write.class.getClassLoader().getResource("origin").getPath();

        File file = new File(path);
        byte[] b = str.getBytes();	//将字符串转换成字节数
        OutputStream out = null;
        try {
            out = new FileOutputStream(file,true);	//实例化OutpurStream
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        //写入
        try {
            out.write(b);		//写入
            out.close();		//关闭
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
