package com.herui.wechatspringbootstarter;

import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("wx.corp")
public class WechatProperties extends WxCpInMemoryConfigStorage{

}
