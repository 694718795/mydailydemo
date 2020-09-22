package com.shu.hadoop.hdfs;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HdfsController {

    @Autowired
    private HadoopTemplate hadoopTemplate;

    /**
     * 将本地文件srcFile,上传到hdfs
     * 参数为: C:\Users\L-shurunlong\Desktop\新建文本文档.txt
     * 报错信息为:  could only be replicated to 0 nodes instead of minReplication (=1).  There are 1 datanode(s) running and 1 node(s) are excluded in this operation.
     * @param srcFile
     * @return
     */
    @RequestMapping("/upload")
    public String upload(@RequestParam String srcFile){
        hadoopTemplate.uploadFile(srcFile);
        return "copy";
    }

    @RequestMapping("/delFile")
    public String del(@RequestParam String fileName){
        hadoopTemplate.delFile(fileName);
        return "delFile";
    }

    @RequestMapping("/download")
    public String download(@RequestParam String fileName,@RequestParam String savePath){
        hadoopTemplate.download(fileName,savePath);
        return "download";
    }
}
