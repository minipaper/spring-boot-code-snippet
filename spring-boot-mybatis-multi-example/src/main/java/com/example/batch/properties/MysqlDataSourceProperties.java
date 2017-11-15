package com.example.batch.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = MysqlDataSourceProperties.PREFIX)
public class MysqlDataSourceProperties implements DatasourceProperties {
    public static final String PREFIX = "datasource.mysql";
    public static final boolean DEFAULT_INITIALIZE = false;

    private boolean initialize = DEFAULT_INITIALIZE;

    private String driverClassName;

    private String url;

    private String userName;

    private String password;

    private int initialSize;

    private int maxActive;

    private int maxIdle;

    private int minIdle;

    private int maxWait;

    @Override
    public boolean isInitialize() {
        return initialize;
    }

    public void setInitialize(boolean initialize) {
        this.initialize = initialize;
    }

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    @Override
    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    @Override
    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    @Override
    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public String toString() {
        return "MysqlDataSourceProperties{" +
                "initialize=" + initialize +
                ", driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", initialSize=" + initialSize +
                ", maxActive=" + maxActive +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                ", maxWait=" + maxWait +
                '}';
    }
}
