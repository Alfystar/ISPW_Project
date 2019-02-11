package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Config{
    ReadWriteLock lock = new ReentrantReadWriteLock();
    private static String confFilePath;
    private Properties configFile;

    private static Config confSingleton = new Config();

    public static Config getInstance(){
        return confSingleton;
    }

    private Config(String path){
        confFilePath = path;
        configFile = new Properties();
        try{

            configFile.load(new FileInputStream(confFilePath));

        }catch (FileNotFoundException e){
            try{
                //set the properties value
                configFile.setProperty("dbHost", "localhost");
                configFile.setProperty("dbuser", "root");
                configFile.setProperty("dbpassword", "0000");

                //save properties to project root folder
                configFile.store(new FileOutputStream(confFilePath), null);

            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        catch(IOException eta){
            eta.printStackTrace();
        }
    }

    private Config()
    {
        this("config.properties");
    }

    public static void main(String[] argv){
        Config cfg = new Config();
        System.out.println(cfg.getProperty("dbHost"));
        cfg.setProprerty("dbHost", "10.200.146.10");
        System.out.println(cfg.getProperty("dbHost"));

    }

    public String getProperty(String key){
        lock.readLock().lock();
        String value = this.configFile.getProperty(key);
        lock.readLock().unlock();
        return value;
    }

    public void setProprerty(String prop, String value){
        try{
            lock.writeLock().lock();
            configFile.load(new FileInputStream(confFilePath));
            configFile.setProperty(prop, value);
            configFile.store(new FileOutputStream(confFilePath), null);
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            lock.writeLock().unlock();

        }
    }

    public static void setConfFilePath(String path)
    {
        confFilePath=path;
    }
}