package cn.sy.demo.conf.charset;

import cn.sy.demo.filter.MutiCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义filter过滤指定url，对其gbk进行转换
 */
@Configuration
public class CharsetConfig {

    @Bean
    public MutiCharacterEncodingFilter mutiCharacterEncodingFilter(){
        MutiCharacterEncodingFilter encodingFilter = new MutiCharacterEncodingFilter();
        encodingFilter.setEncoding("GBK");
        encodingFilter.setMutiCharset("GBK");
        encodingFilter.setForceRequestEncoding(true);
        encodingFilter.setForceResponseEncoding(true);
        List<String> urls = new ArrayList<>();
        urls.add("/xxx/xx");
        encodingFilter.setMutiUrls(urls);
        return encodingFilter;
    }
}
