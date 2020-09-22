package cn.shu;

import java.io.*;

public class ReadFromOrigin {
    public static void main(String[] args) throws IOException {
//        String read = read(10);

        String s = read1(3, 7);   //[x,y)
        System.out.println(s);
    }

    public static String read(long n) throws IOException {
        String path = "G:"+ File.separator+"javademo"+File.separator+"srl"+File.separator+"queue"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"origin";
        File file = new File(path);
//        指定文件
        FileInputStream fis = new FileInputStream(file);
//        指定位置
        fis.skip(n);
//        指定长度
        byte[] bs = new byte[1];
//        得到内容
        int read = fis.read(bs);
        System.out.println(read);
        return null;
    }

    public static String read1(int from,int to){
        String path = "G:"+ File.separator+"javademo"+File.separator+"srl"+File.separator+"queue"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"origin";

        String result="";
        try{
            FileInputStream fis=new FileInputStream(path);
            BufferedInputStream bis=new BufferedInputStream(fis);
            bis.skip(from-1);
            int c=0;
            for(int i=0;(i<to-from)&&(c=bis.read())!=-1;i++){
                result+=(char)c;
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
