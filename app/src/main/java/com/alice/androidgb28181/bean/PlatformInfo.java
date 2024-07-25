package com.alice.androidgb28181.bean;

/**
 *
 * @author Des：平台信息
 */
public class PlatformInfo {
    private String serverId;
    private String domain;
    private String serverurl;
    private int serverPort;
    private int localPort;
    private String userName;
    private String password;
    private String deviceId;
    private String channelId;

    private int registerInterval;
    private int heartbeatInterval;
    private int gpsInterval;


    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getServerurl() {
        return serverurl;
    }

    public void setServerurl(String serverurl) {
        this.serverurl = serverurl;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public int getRegisterInterval() {
        return registerInterval;
    }

    public void setRegisterInterval(int registerInterval) {
        this.registerInterval = registerInterval;
    }

    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(int heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public int getGpsInterval() {
        return gpsInterval;
    }

    public void setGpsInterval(int gpsInterval) {
        this.gpsInterval = gpsInterval;
    }
}
