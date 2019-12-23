package com.test.model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//服务器配置

@Component
@PropertySource({"classpath:application.properties"})
@ConfigurationProperties(prefix="test")
//这里加了个test前缀
public class ServerSettings {

    //这是不需要写vlaue标签，只要text.name去掉前缀test后的name和这里name相同，就会自动赋值
    private String name;
 
    //域名地址
    private String domain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
