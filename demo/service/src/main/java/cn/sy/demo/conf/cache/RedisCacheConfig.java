package cn.sy.demo.conf.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory){
        RedisCacheManager cacheManager= RedisCacheManager.create(connectionFactory);
        //默认超时时间
//        cacheManager.setDefaultExpiration(200*10);
//        //设置value=xxx的超时时间
//        Map<String,Long> expiresMap=new HashMap<>();
//        expiresMap.put("user",600L);
//        cacheManager.setExpires(expiresMap);
        return cacheManager;
    }

}
