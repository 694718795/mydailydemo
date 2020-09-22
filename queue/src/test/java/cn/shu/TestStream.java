package cn.shu;


import java.io.*;


public class TestStream {

    /*public static void main(String[] args) {

        try {

            // 准备文件666.txt其中的内容是空的
            String path = Write_write.class.getClassLoader().getResource("666.txt").getPath();
//            File f1 = new File("E:/file/LOL/666.txt");
            File f1 = new File(path);

            if (f1.exists()==false){

                f1.getParentFile().mkdirs();

            }

            // 准备长度是2的字节数组，用88,89初始化，其对应的字符分别是X,Y

            byte data[] = {88,89};

            // 创建基于文件的输出流

            FileOutputStream fos = new FileOutputStream(f1);

            // 把数据写入到输出流

            fos.write(data);

            // 关闭输出流

            fos.close();

            System.out.println("输入完成");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }*/

    public static void main(String[] args) throws IOException {
//        writeFile();
        write2();
    }

    public static void writeFile() {
        try {
            String path = Write_write.class.getClassLoader().getResource("666.txt").getPath();
            File writeName = new File(path); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (
                    FileWriter writer = new FileWriter(writeName,true);
                    BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("我会写入文件啦1\r\n"); // \r\n即为换行
                out.write("我会写入文件啦2\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void write2() throws IOException {
        String word = "hahahahhahahahhah112";
        FileOutputStream fileOutputStream = null;
        File file = new File("F:\\test.txt");
//        File file = new File("G:\\javademo\\srl\\queue\\target\\classes\\666.txt");
//        String path = Write_write.class.getClassLoader().getResource("666.txt").getPath();
//        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        //第二个参数决定了是否以追加的方式写入,不写默认为flase, 设置为true时为追加
        fileOutputStream = new FileOutputStream(file,true);
        fileOutputStream.write(word.getBytes("gbk"));
        fileOutputStream.flush();
        fileOutputStream.close();
    }


}
