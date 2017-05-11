package com.my.example.dashboard.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonExecutors implements DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(CommonExecutors.class);
    private static final ConcurrentHashMap<PoolConfigs, ExecutorService> EXECUTORS = new ConcurrentHashMap<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                shutdown();
            }
        }));
    }

    public static ExecutorService get(PoolConfigs poolConfigs) {
        if (null == EXECUTORS.get(poolConfigs)) {
            synchronized (CommonExecutors.class) {
                if (null == EXECUTORS.get(poolConfigs)) {
                    int poolSize = poolConfigs.getPoolSize();
                    if (poolSize > 0) {
                        EXECUTORS.put(poolConfigs, Executors.newFixedThreadPool(poolConfigs.getPoolSize()));
                        logger.info("Init executorService for {} with poolSize {}", poolConfigs, poolConfigs.getPoolSize());
                    }
                }
            }
        }

        return EXECUTORS.get(poolConfigs);
    }

    private static void shutdown() {
        if (null == EXECUTORS || EXECUTORS.isEmpty()) {
            return;
        }

        System.out.println("Start to shutdown ExecutorService......");
        for (Map.Entry<PoolConfigs, ExecutorService> entry: EXECUTORS.entrySet()) {
            PoolConfigs productPool = entry.getKey();
            String poolName = productPool.getPoolName();
            int poolSize = productPool.getPoolSize();
            try {
                ExecutorService executorService = entry.getValue();
                if (executorService.isShutdown()) {
                    System.out.println("=> ExecutorService " + poolName + ":" + poolSize + " is already shutdown");
                } else {
                    executorService.shutdownNow();
                    System.out.println("=> Success to shutdown " + poolName + ":" + poolSize + " executorService");
                }
            } catch (Exception e) {
                System.out.println("Error to shutdown " + poolName + ":" + poolSize + " executorService");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() throws Exception {
        shutdown();
    }
}
