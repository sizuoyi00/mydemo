package cn.sy.demo.conf.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 默认情况下，@Scheduled 注解的任务是由一个单线程的线程池进行调度的。
 * 这样会导致应用内的定时任务只能串行执行。
 * 比如说定时任务A,定时任务B都是每分钟执行一次，
 * 默认单线程的话就会导致每次只能执行完一个才能执行另外一个。
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}
}