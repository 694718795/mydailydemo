package cn.shu;

import java.io.*;

public class Count {
    public static void main(String[] args) throws FileNotFoundException {
        int origin = countbyname("origin");
        System.out.println("origin中的字符长度"+origin);
        int destination = countbyname("destination");
        System.out.println("destination中的字符长度"+destination);
    }

    public static int countbyname(String filename) throws FileNotFoundException {
        //获取resource目录下的文件
//        String path = Write_write.class.getClassLoader().getResource(filename).getPath();
        String path = "G:"+ File.separator+"javademo"+File.separator+"srl"+File.separator+"queue"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+filename;

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
}
