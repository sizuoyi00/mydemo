package com.test.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadCancelTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadFactory workerFactory = new ThreadFactoryBuilder()
                .setNameFormat("APLTask-%d")
                .setDaemon(true)
                .build();
        FutureTask futureTask = new FutureTask(() -> {
            System.out.println("FutureTask.Runnable " + Thread.currentThread().getId() + "---" + Thread.currentThread().getName());
            return "Result";
        });
        Runnable runnable = getTask(true, futureTask);
        ThreadPoolExecutor debugExecutor = new ThreadPoolExecutor(5, 5, 30L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), workerFactory);
        debugExecutor.submit(runnable);
        Object o = futureTask.get();
        System.out.println(o);
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
