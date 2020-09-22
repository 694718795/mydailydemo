package cn.shu;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Write_write {
    private AtomicBoolean cCanRead = new AtomicBoolean(false);

    @Test
    public void write() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            writeStr(i+"");
//            System.out.println(i);
//            Thread.sleep(1000);
        }
    }

    @Test
    public void count() throws FileNotFoundException {
        int origin = countbyname("origin");
        System.out.println("origin中的字符长度"+origin);
        int destination = countbyname("destination");
        System.out.println("origin中的字符长度"+destination);
    }

    public int countbyname(String filename) throws FileNotFoundException {
        //获取resource目录下的文件
//        String path = Write_write.class.getClassLoader().getResource(filename).getPath();
        String path = "G:"+File.separator+"javademo"+File.separator+"srl"+File.separator+"queue"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+filename;

        System.out.println("path:"+path);
        // TODO Auto-generated method stub
        File f1 = new File(path);
        StringBuilder stringBuilder = new StringBuilder();
        if (f1.isDirectory()) {
            System.out.println(path + " is a directory");
        } else {
            BufferedReader br = new BufferedReader(new FileReader(f1));
            String st;
            try {
                while(null != (st = br.readLine())){
//                    System.out.println(st);
                    stringBuilder.append(st);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
//        System.out.println(stringBuilder);
        int length = stringBuilder.toString().length();
        return length;
    }

    public void writeStr(String str){

        //定义文件路径，没有该文件会自动创建，如果路径有文件夹，一定要有，不会自动创建文件夹
//        String filename = "e:"+File.separator+"a"+File.separator+"b.txt";
        String path = "G:"+File.separator+"javademo"+File.separator+"srl"+File.separator+"queue"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"origin";
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
