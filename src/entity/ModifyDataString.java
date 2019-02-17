package entity;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class ModifyDataString{

    protected ReadWriteLock lock = new ReentrantReadWriteLock();


    public abstract String get();

    public abstract void set(String dataString);
}
