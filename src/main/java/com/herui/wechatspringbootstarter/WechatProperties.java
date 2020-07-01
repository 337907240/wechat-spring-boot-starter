package com.herui.wechatspringbootstarter;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ConfigurationProperties("wx.corp")
public class WechatProperties implements WxCpConfigStorage, Serializable {
    private static final long serialVersionUID = 1154541446729462780L;
    private volatile String corpId;
    private volatile String corpSecret;
    private volatile String token;
    private volatile String accessToken;
    private Lock accessTokenLock = new ReentrantLock();
    private volatile String aesKey;
    private volatile Integer agentId;
    private volatile long expiresTime;
    private volatile String oauth2redirectUri;
    private volatile String httpProxyHost;
    private volatile int httpProxyPort;
    private volatile String httpProxyUsername;
    private volatile String httpProxyPassword;
    private volatile String jsapiTicket;
    private Lock jsapiTicketLock = new ReentrantLock();
    private volatile long jsapiTicketExpiresTime;
    private volatile String agentJsapiTicket;
    private Lock agentJsapiTicketLock = new ReentrantLock();
    private volatile long agentJsapiTicketExpiresTime;
    private volatile File tmpDirFile;
    private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;
    private volatile String baseApiUrl;

    public WechatProperties() {
    }

    public void setBaseApiUrl(String baseUrl) {
        this.baseApiUrl = baseUrl;
    }

    public String getApiUrl(String path) {
        if (this.baseApiUrl == null) {
            this.baseApiUrl = "https://qyapi.weixin.qq.com";
        }

        return this.baseApiUrl + path;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public Lock getAccessTokenLock() {
        return this.accessTokenLock;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    public void expireAccessToken() {
        this.expiresTime = 0L;
    }

    public synchronized void updateAccessToken(WxAccessToken accessToken) {
        this.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (long) (expiresInSeconds - 200) * 1000L;
    }

    public String getJsapiTicket() {
        return this.jsapiTicket;
    }

    public Lock getJsapiTicketLock() {
        return this.jsapiTicketLock;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket;
    }

    public long getJsapiTicketExpiresTime() {
        return this.jsapiTicketExpiresTime;
    }

    public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
        this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
    }

    public boolean isJsapiTicketExpired() {
        return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
    }

    public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        this.jsapiTicket = jsapiTicket;
        this.jsapiTicketExpiresTime = System.currentTimeMillis() + (long) (expiresInSeconds - 200) * 1000L;
    }

    public String getAgentJsapiTicket() {
        return this.agentJsapiTicket;
    }

    public Lock getAgentJsapiTicketLock() {
        return this.agentJsapiTicketLock;
    }

    public boolean isAgentJsapiTicketExpired() {
        return System.currentTimeMillis() > this.agentJsapiTicketExpiresTime;
    }

    public void expireAgentJsapiTicket() {
        this.agentJsapiTicketExpiresTime = 0L;
    }

    public void updateAgentJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        this.agentJsapiTicket = jsapiTicket;
        this.agentJsapiTicketExpiresTime = System.currentTimeMillis() + (long) (expiresInSeconds - 200) * 1000L;
    }

    public void expireJsapiTicket() {
        this.jsapiTicketExpiresTime = 0L;
    }

    public String getCorpId() {
        return this.corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return this.corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresTime() {
        return this.expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getAesKey() {
        return this.aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public Integer getAgentId() {
        return this.agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getOauth2redirectUri() {
        return this.oauth2redirectUri;
    }

    public void setOauth2redirectUri(String oauth2redirectUri) {
        this.oauth2redirectUri = oauth2redirectUri;
    }

    public String getHttpProxyHost() {
        return this.httpProxyHost;
    }

    public void setHttpProxyHost(String httpProxyHost) {
        this.httpProxyHost = httpProxyHost;
    }

    public int getHttpProxyPort() {
        return this.httpProxyPort;
    }

    public void setHttpProxyPort(int httpProxyPort) {
        this.httpProxyPort = httpProxyPort;
    }

    public String getHttpProxyUsername() {
        return this.httpProxyUsername;
    }

    public void setHttpProxyUsername(String httpProxyUsername) {
        this.httpProxyUsername = httpProxyUsername;
    }

    public String getHttpProxyPassword() {
        return this.httpProxyPassword;
    }

    public void setHttpProxyPassword(String httpProxyPassword) {
        this.httpProxyPassword = httpProxyPassword;
    }

    public String toString() {
        return WxCpGsonBuilder.create().toJson(this);
    }

    public File getTmpDirFile() {
        return this.tmpDirFile;
    }

    public void setTmpDirFile(File tmpDirFile) {
        this.tmpDirFile = tmpDirFile;
    }

    public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
        return this.apacheHttpClientBuilder;
    }

    public boolean autoRefreshToken() {
        return true;
    }

    public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
        this.apacheHttpClientBuilder = apacheHttpClientBuilder;
    }
}