package com.herui.wechatspringbootstarter;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WechatProperties.class)
@ConditionalOnClass(WxCpMessage.class)
public class WechatAutoConfiguration {


    @Bean
    public WxCpService wxCpService(WechatProperties wechatProperties) {
        WxCpService service = new WxCpServiceImpl();
        service.setWxCpConfigStorage(wechatProperties);
        return service;
    }
}
