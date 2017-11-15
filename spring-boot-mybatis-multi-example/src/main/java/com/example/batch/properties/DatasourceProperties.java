package com.example.batch.properties;

public interface DatasourceProperties {
    String getDriverClassName();

    String getUrl();

    String getUserName();

    String getPassword();

    boolean isInitialize();

    int getInitialSize();

    int getMaxActive();

    int getMaxIdle();

    int getMinIdle();

    int getMaxWait();
}
