package DAO;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Config{
    ReadWriteLock lock = new ReentrantReadWriteLock();
    private static String confFilePath = "/config.properties";
    private Properties configFile;

    private static Config confSingleton = new Config();

    public static Config getInstance(){ return confSingleton; }

    private Config(){
        configFile = new Properties();
        File myFile = new File(System.getProperty("user.home"), ".config/config.properties");  //or "user.home"
        confFilePath=System.getProperty("user.home") + "/.config/config.properties";
        try{

            configFile.load(new FileInputStream(confFilePath));

        }catch (FileNotFoundException e){
            createFile();
            try{
                configFile.load(new FileInputStream(confFilePath));
            } catch(IOException eta){
                System.err.println("Non si vuole creare il file, attuale path: "+ confFilePath);
                eta.printStackTrace();
            }
        }
        catch(IOException eta){
            eta.printStackTrace();
        }
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
        }catch (FileNotFoundException e){
            System.out.println("setProprerty, file not found exeption");
            createFile();
            //setProprerty(prop,value);
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            lock.writeLock().unlock();

        }
    }

    private void createFile()
    {
        System.out.println("Dentro createFile");
        try{
            //set the properties value
            configFile.setProperty("dbHost", "localhost");
            configFile.setProperty("dbuser", "root");
            configFile.setProperty("dbpassword", "0000");

            //save properties to project root folder
            //System.out.println(System.getProperty("user.home"));
            File myFile = new File(System.getProperty("user.home"), ".config/config.properties");  //or "user.home"
            myFile.createNewFile();
            confFilePath=myFile.getPath();
            configFile.store(new FileOutputStream(myFile.getPath()), null);

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}