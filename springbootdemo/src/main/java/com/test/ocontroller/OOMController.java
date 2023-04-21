package com.test.ocontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OOMController {

    @GetMapping("oomexit")
    public void oomexit() {
        // 设置JVM参数，最大堆内存为20M
        //-Xmx20m -Xms20m -XX:+HeapDumpOnOutOfMemoryError -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/suxun/IdeaProjects/mydemo/springbootdemo/logs
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            // 不断向list中添加byte数组，占用内存
            list.add(new byte[10 * 1024 * 1024]);
        }

        System.out.println("oom end !!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @GetMapping("oomnotexit")
    public void oomnotexit() {
        // 设置JVM参数，最大堆内存为20M
        //-Xmx20m -Xms20m -XX:+ExitOnOutOfMemoryError -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/suxun/IdeaProjects/mydemo/springbootdemo/logs
        List<byte[]> list = new ArrayList<>();
        try {
            while (true) {
                // 不断向list中添加byte数组，占用内存
                list.add(new byte[10 * 1024 * 1024]);
            }
        } catch (OutOfMemoryError e) {
            // 打印堆栈信息
            e.printStackTrace();
        }

        System.out.println("oom end !!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
