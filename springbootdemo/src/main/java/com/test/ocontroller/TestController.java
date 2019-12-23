package com.test.ocontroller;

import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@RestController
public class TestController {


    public static void main(String[] args) {


        Executors.newFixedThreadPool(1);
        PriorityBlockingQueue blockingQueue = new PriorityBlockingQueue();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 6, 0, TimeUnit.SECONDS,
                blockingQueue);
        new ThreadPoolExecutor


        List<Integer> list = Arrays.asList(1, 2, 5);
        AtomicInteger count = new AtomicInteger();
        list.forEach(l -> count.incrementAndGet(l));
        System.out.println(count);

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.lock();
        ReentrantLock reentrantLock1 = new ReentrantLock();
        reentrantLock1.lock();
        reentrantLock1.lock();

        reentrantLock.lock();


//        new Thread(() -> {
//            reentrantLock.lock();
//            try {
//                Thread.sleep(1000000000);
//            } catch (InterruptedException e) {
//            }
//            reentrantLock.unlock();
//        }).start();
//
//        Thread.sleep(10900);

        new Thread(reentrantLock::lock).start();

//        reentrantLock.lock();
//
//        reentrantLock.tryLock(2, TimeUnit.SECONDS);
//        reentrantLock.lock();
//
//        reentrantLock.unlock();
//        reentrantLock.unlock();
        reentrantLock.unlock();


        String url = "jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = conn.prepareStatement("select * from user where id = ?");
            statement.setLong(1, 1);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                System.out.println(set.getString("name"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping("/vq/test")
    public String getTest() {

        return "我是测试返回值";
    }

    @RequestMapping("/oom/test")
    public String testOOM() {
        JSONObject data = new JSONObject();
        data.put("AppId", "APPID");
        data.put("Secret", "HASHKEY");
        data.put("TransCode", "TRANSCODETOKEN");
        String rsaContent = buildParam(data.toString());
        return rsaContent;
    }

    private BouncyCastleProvider provider = new BouncyCastleProvider();


    /**
     * 这是个实际遇到的问题，关于api调用加密解密的参数拼接
     * 简化了业务，只保留了问题关注点代码，也就是new BouncyCastleProvider()
     *
     * @param data
     * @return
     */
    private String buildParam(String data) {

        try {
            Cipher cipher = Cipher.getInstance("RSA", provider);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject value = new JSONObject();
        value.put("Data", data);
        value.put("Access_Token", "token");
        JSONObject content = new JSONObject();
        content.put("content", value);
        return content.toString();
    }

}
