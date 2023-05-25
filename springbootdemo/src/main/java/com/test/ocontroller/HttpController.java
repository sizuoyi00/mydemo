package com.test.ocontroller;

import com.alibaba.fastjson.JSON;
import com.test.model.JsonData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("http")
@PropertySource({"classpath:application.properties"})
public class HttpController {

    @PostMapping("execute")
    public String execute(HttpServletRequest httpServletRequest) throws IOException {
        byte[] bytes = IOUtils.toByteArray(httpServletRequest.getInputStream());
        return new String(bytes);
    }

    @RequestMapping(value = "multipartBody")
    @ResponseBody
    public JsonData multipartBody(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {

        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File("/Users/suxun/Desktop/image/" + "mumumupart" + ".xlsx"));
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        String name = request.getParameter("name");
        if (name == null) {
            throw new RuntimeException("name is null");
        }


        String id = request.getParameter("id");
        if (id == null) {
            throw new RuntimeException("id is null");
        }
        return new JsonData(200, "ok ok ok", id + name);
    }

    @RequestMapping(value = "multipartBody2")
    @ResponseBody
    public JsonData multipartBody2(HttpServletRequest request) throws IOException {

//        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File("/Users/suxun/Desktop/image/" + "mumumupart" + ".xlsx"));
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        String name = request.getParameter("name");
        if (name == null) {
            throw new RuntimeException("name is null");
        }


        String id = request.getParameter("id");
        if (id == null) {
            throw new RuntimeException("id is null");
        }
        return new JsonData(200, "ok ok ok", id + name);
    }

    @RequestMapping(value = "inputStreamBody")
    @ResponseBody
    public JsonData inputStreamBody(HttpServletRequest request) throws IOException {
        FileUtils.copyInputStreamToFile(request.getInputStream(), new File("/Users/suxun/Desktop/image/" + "inininput" + ".xlsx"));
        return new JsonData(200, "ok ok ok", null);
    }


    @RequestMapping(value = "stringBody")
    @ResponseBody
    public JsonData stringBody(HttpServletRequest request, @RequestBody Map map) throws IOException {
        Assert.assertTrue(!CollectionUtils.isEmpty(map));
        System.out.println(map);
        return new JsonData(200, "ok ok ok", map);
    }


    @RequestMapping(value = "formBody")
    @ResponseBody
    public JsonData formBody(HttpServletRequest request) {
        Assert.assertTrue(request.getParameterMap() != null);
        System.out.println(JSON.toJSONString(request.getParameterMap()));
        return new JsonData(200, "ok ok ok", request.getParameterMap());
    }
}
