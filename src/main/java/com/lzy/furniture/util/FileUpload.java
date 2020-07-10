package com.lzy.furniture.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUpload {
    public String uploadFile(MultipartFile file, String filePath) {
        String fileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        try {
            File directory = new File(filePath);
            if(!directory.exists()){
                directory.mkdirs();
            }
            file.transferTo(new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/"+ fileName;

    }

    public String uploadFile(MultipartFile file, String fileBasePath, String fileMiddlePath) {
        String fileBaseName = UUID.randomUUID().toString();
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);


        try {
            File directory = new File(fileBasePath+fileMiddlePath);
            if(!directory.exists()){
                directory.mkdirs();
            }
            file.transferTo(new File(fileBasePath + fileMiddlePath +  fileBaseName + "." + fileType));
            Thumbnails
                    .of(fileBasePath + fileMiddlePath +  fileBaseName + "." + fileType)
                    .width(350)
                    .height(350)
                    .toFile(fileBasePath + fileMiddlePath +  fileBaseName + "_mini." + fileType);

            Thumbnails
                    .of(fileBasePath + fileMiddlePath +  fileBaseName + "." + fileType)
                    .width(1920)
                    .height(1920)
                    .toFile(fileBasePath + fileMiddlePath +  fileBaseName + "_large." + fileType);

            Thumbnails
                    .of(fileBasePath + fileMiddlePath +  fileBaseName + "." + fileType)
                    .width(1080)
                    .height(1080)
                    .toFile(fileBasePath + fileMiddlePath +  fileBaseName + "_middle." + fileType);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/"+ fileMiddlePath +  fileBaseName + "." + fileType;

    }



}
