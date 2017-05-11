package com.my.example.dashboard.common.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date:17/3/30
 * Time:下午6:06
 *
 * @author yongquan.wen@corp.elong.com
 */
public class MongoManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoManager.class);

    private String host;

    private int port;

    private MongoOptions mongoOptions;

    private MongoClient mongoClient;

    public void init() {
        LOGGER.info("initializing mongo component");
        MongoClientOptions options = mongoOptions.build();
        try {
            mongoClient = new MongoClient(new ServerAddress(host, port), options);
            LOGGER.info("mongo component init success");
        } catch (Exception e) {
            LOGGER.error("mongo component init fail");
        }

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public MongoOptions getMongoOptions() {
        return mongoOptions;
    }

    public void setMongoOptions(MongoOptions mongoOptions) {
        this.mongoOptions = mongoOptions;
    }

    public MongoDatabase getDatabase(String dbName) {
        return mongoClient.getDatabase(dbName);
    }

    public MongoCollection getCollection(String dbName, String collectionName) {
        return mongoClient.getDatabase(dbName).getCollection(collectionName);
    }

    public <T> MongoCollection getCollection(String dbName, String collectionName, Class<T> clazz) {
        return mongoClient.getDatabase(dbName).getCollection(collectionName, clazz);
    }


}
