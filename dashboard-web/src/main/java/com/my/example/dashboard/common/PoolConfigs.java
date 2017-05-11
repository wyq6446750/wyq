package com.my.example.dashboard.common;

public enum PoolConfigs {
    // 默认线程池大小
    DEFAULT("DEFAULT", 50);


    private String poolName;
    private int poolSize;

    private PoolConfigs(String poolName, int poolSize) {
        this.poolName = poolName;
        this.poolSize = poolSize;
    }

    public String getPoolName() {
        return poolName;
    }

    public int getPoolSize() {
        return poolSize;
    }
}
