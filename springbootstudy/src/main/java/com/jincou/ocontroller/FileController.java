package com.jincou.ocontroller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;


import com.jincou.model.JsonData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@PropertySource({"classpath:application.properties"})
public class FileController {

	   //文件放在项目的images下
    //	private   String filePath =  "C:\\Users\\chenww\\Desktop\\springbootstudy\\springbootstudy\\src\\main\\resources\\static\\images\\";
	@Value("${web.file.path}")
	private String filePath;
	 	@RequestMapping(value = "upload")
	    @ResponseBody
	    public JsonData upload(@RequestParam("head_img") MultipartFile file,HttpServletRequest request) {


			System.out.println("地址："+filePath);
			System.out.println("地址："+filePath);


	 		//file.isEmpty(); 判断图片是否为空
	 		//file.getSize(); 图片大小进行判断
	 		
	 		String name = request.getParameter("name");
	 		System.out.println("用户名："+name);

			System.out.println("用户名："+name);
	 		// 获取文件名
	        String fileName = file.getOriginalFilename();	        
	        System.out.println("上传的文件名为：" + fileName);
	        
	        // 获取文件的后缀名,比如图片的jpeg,png
	        String suffixName = fileName.substring(fileName.lastIndexOf("."));
	        System.out.println("上传的后缀名为：" + suffixName);

	        // 文件上传后的路径
	        fileName = UUID.randomUUID() + suffixName;
	        System.out.println("转换后的名称:"+fileName);

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
	        return  new JsonData(-1, "fail to save ", null);
	    }
}
