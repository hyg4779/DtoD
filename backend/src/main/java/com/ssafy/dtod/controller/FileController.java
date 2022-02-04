package com.ssafy.dtod.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @PostMapping(value = "/upload/profile-img")
    @ResponseBody
    public String requestUploadFile(@RequestParam("file") MultipartFile file) {
        try {
                String path = "./profile";
                String savedFileName = file.getOriginalFilename();
                File f = new File(path);
                if (!f.exists())
                    f.mkdir();
                FileOutputStream writer = new FileOutputStream(path + "/" + savedFileName);
                writer.write(file.getBytes());
                writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "upload fail";
        }
        return "upload success";
    }
}
