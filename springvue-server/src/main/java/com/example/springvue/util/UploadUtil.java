package com.example.springvue.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @Description: 文件上传 TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/22 23:17
 */
public class UploadUtil {
    private static String IMAGE_SAVE_PATH = "C:/sch/Image/";
    private static String IMAGE_USE_PATH = "http://localhost:8088/image/";

    public static String uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        } else {
            try {
                String fileName = file.getOriginalFilename();
                String[] suffixNames = fileName.split("\\.");
                String newFileName = UUID.randomUUID().toString() + "." + suffixNames[suffixNames.length - 1];
                String fileSavePath = IMAGE_SAVE_PATH + newFileName;
                file.transferTo(new File(fileSavePath));
                String fileUsePath = IMAGE_USE_PATH + newFileName;

                return fileUsePath;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
