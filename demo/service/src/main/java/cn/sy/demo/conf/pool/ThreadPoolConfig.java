package cn.sy.demo.conf.pool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor bean = new ThreadPoolTaskExecutor();
        bean.setCorePoolSize(20);
        bean.setMaxPoolSize(50);
        /**
         * 默认60s
         */
        bean.setKeepAliveSeconds(60);
        /**
         * 任意一个正数会生成LinkedBlockingQueue  负值生成SynchronousQueue
         */
        bean.setQueueCapacity(Integer.MAX_VALUE);
        /**
         1、AbortPolicy：直接抛出异常。
         2、CallerRunsPolicy：只用调用者所在线程来运行任务。
         3、DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
         4、DiscardPolicy：不处理，丢弃掉。
         默认第一种
         */
        bean.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return bean;
    }

}
