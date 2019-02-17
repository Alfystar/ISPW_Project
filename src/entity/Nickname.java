package entity;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Nickname{
    private String nickname;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Nickname(String nickname){
        this.nickname = nickname;
    }

    public Nickname(){
        this.nickname = "";
    }

    public Nickname(Nickname nickname){
        this.nickname = nickname.get();
    }

    public String get(){
        try{
            lock.readLock().lock();
            return this.nickname;
        }finally{
            lock.readLock().unlock();
        }
    }

    public void set(String newNickname){

        try{
            lock.writeLock().lock();
            this.nickname = newNickname;
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.nickname.equals(((Nickname) o).nickname);
        }catch(ClassCastException e){
            return false;
        }
    }
}