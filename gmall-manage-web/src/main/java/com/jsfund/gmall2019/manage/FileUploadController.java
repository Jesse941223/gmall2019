package com.jsfund.gmall2019.manage;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jsfund.gmall2019.usermanage.service.ManageService;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@CrossOrigin //跨域
public class FileUploadController {
    private static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);
    @Reference
    private ManageService service;
    @Value("${fileServer.url}")
    private String fileUrl;


    @RequestMapping("fileUpload")
    public String  fileUpload(MultipartFile file) throws IOException, MyException {
        //最终生成的图片全路径
        String imageUrl = fileUrl;
        //前提文件不等于空
        if (null != file) {
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            //初始化
            ClientGlobal.init(configFile);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //文件名
            String filename = file.getOriginalFilename();
            LOG.info("文件名称：" + filename);
            // 获取文件的后缀名
            String extName = StringUtils.substringAfter(filename, ".");
            LOG.info("文件后缀名称：" + extName);
            //String orginalFilename = "D://image//黑背.jpg";
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);// 上传字节文件

            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];
                LOG.info("path:" + path);
                //拼装路径
                imageUrl += "/"+path;
//			s = group1
//          s = M00/00/00/wKjCgF8TDY-AVnLfAACbuutfIxI335.jpg
            }
        }
        // return "http://192.168.194.128/group1/M00/00/00/wKjCgF8TDY-AVnLfAACbuutfIxI335.jpg";
        return imageUrl;
    }
}
