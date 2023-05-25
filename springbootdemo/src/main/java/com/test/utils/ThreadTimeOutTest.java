package com.test.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.concurrent.*;

public class ThreadTimeOutTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadFactory workerFactory = new ThreadFactoryBuilder()
                .setNameFormat("APLTask-%d")
                .setDaemon(true)
                .build();
        FutureTask futureTask = new FutureTask(() -> {
            File file = new File("/Users/suxun/Desktop/tmp/apl.txt");
            for (int i = 0; i < 1000000; i++) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("中断");
                }
                FileUtils.write(file, i + " ", true);
            }
            return "Result";
        });
        Runnable runnable = getTask(false, futureTask);
        ThreadPoolExecutor debugExecutor = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), workerFactory);
        debugExecutor.submit(runnable);
        Object o = null;
        try {
            o = futureTask.get(40, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            futureTask.cancel(true);
            e.printStackTrace();
        }
        System.out.println("hahahahahahha" + o);
        Thread.sleep(10000);

    }

    private static Runnable getTask(boolean flag, FutureTask futureTask) {
        return () -> {
            System.out.println("Runnable " + Thread.currentThread().getId() + "---" + Thread.currentThread().getName());
            try {
                if (flag) {
                    throw new RuntimeException("中断");
                }
            } catch (RuntimeException e) {
                futureTask.cancel(true);
            }
            futureTask.run();
        };
    }
}
