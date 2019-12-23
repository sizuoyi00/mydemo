package cn.sy.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author guest1
 */
@RestController
@RequestMapping("jvm")
public class JVMController {

    public static void main(String[] args) {
        get();
    }

    /**
     * 线程堆栈
     */
    @GetMapping("thread")
    public static void get(){

        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()){
            final Thread key = stackTrace.getKey();
            final StackTraceElement[] value = stackTrace.getValue();
            if(key.equals(Thread.currentThread())){
                continue;
            }
            System.out.println(key.getName());
            for (StackTraceElement stackTraceElement : value) {
                System.out.println(stackTraceElement);
            }
        }
    }
}
