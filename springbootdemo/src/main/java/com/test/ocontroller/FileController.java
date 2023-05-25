package com.test.ocontroller;

import com.test.model.JsonData;
import com.test.utils.ResponseUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
@PropertySource({"classpath:application.properties"})
public class FileController {

    @Value("${web.file.path}")
    private String filePath;


    @PostMapping("download")
    public void execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + UUID.randomUUID() + ".xlsx");
        ResponseUtils.writeToResponse(new FileInputStream("/Users/suxun/Desktop/image/download.xlsx"), httpServletResponse);
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public JsonData upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {


        System.out.println("地址：" + filePath);
        System.out.println("地址：" + filePath);
        if (file == null) {
            throw new RuntimeException("文件为空");
        }

        String tenant_id = request.getParameter("tenant_id");
        System.out.printf("tenant_id" + tenant_id);

        //file.isEmpty(); 判断图片是否为空
        //file.getSize(); 图片大小进行判断
        String name = request.getParameter("name");
        System.out.println("用户名：" + name);

        System.out.println("用户名：" + name);
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);

        // 获取文件的后缀名,比如图片的jpeg,png
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);

        // 文件上传后的路径
        fileName = UUID.randomUUID() + suffixName;
        System.out.println("转换后的名称:" + fileName);

        File dest = new File(filePath + fileName);

        try {
            file.transferTo(dest);
            //上传成功
            return new JsonData(0, fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //上传失败
        return new JsonData(-1, "fail to save ", null);
    }
}
