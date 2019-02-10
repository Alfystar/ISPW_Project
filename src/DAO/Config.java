package DAO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.*;
import java.util.Properties;

public class Config {
    private Properties configFile;
    private String confFile = "config.properties";
    ReadWriteLock lock = new ReentrantReadWriteLock();

    public Config() {
        configFile = new Properties();
        try {

            configFile.load(new FileInputStream(confFile));

        } catch (IOException eta) {
            eta.printStackTrace();
        }
    }

    public String getProperty(String key) {
        lock.readLock().lock();
        String value = this.configFile.getProperty(key);
        lock.readLock().unlock();
        return value;
    }

    public void setProprerty(String prop, String value) {
        try {
            lock.writeLock().lock();
            configFile.load(new FileInputStream(confFile));
            configFile.setProperty(prop, value);
            configFile.store(new FileOutputStream(confFile), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            lock.writeLock().unlock();

        }
    }

    public static void main(String[] argv)
    {
        Config cfg = new Config();
        System.out.println(cfg.getProperty("dbHost"));
        cfg.setProprerty("dbHost","10.200.146.10");
        System.out.println(cfg.getProperty("dbHost"));

    }
}