package cn.sy.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @EnableRedisHttpSession - spring boot提供的用redis解决session共享的方法
 * 配置redis服务器的连接
 * maxInactiveIntervalInSeconds 默认是1800秒过期
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

}