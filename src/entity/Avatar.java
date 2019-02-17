package entity;

import javafx.scene.image.Image;

import java.util.Vector;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Avatar{
    private String[] nameAvatar = {"default-Avatar", "rocket-Avatar", "girl-Avatar", "man-Avatar", "girlStudent-Avatar", "manStudent-Avatar"};
    private Vector<Image> icons = new Vector<Image>();
    private Image myIcon;
    private int indexImage;

    private ReadWriteLock lock = new ReentrantReadWriteLock();


    public Avatar(){
        Image img;
        for(String str : nameAvatar){
            img = new Image(getClass().getResourceAsStream("/ImageFile/Avatar/" + str + ".png"));
            icons.add(img);
        }
        this.myIcon = icons.get(0);
        this.indexImage = 0;
    }

    public Avatar(String name){
        this();
        setMyIcon(name);
    }

    public static void main(String[] argv){
        Avatar iconAvatar = new Avatar();
    }

    public Image getMyIcon(){
        try{
            lock.readLock().lock();
            return this.myIcon;
        }finally{
            lock.readLock().unlock();
        }

    }

    public void setMyIcon(int index){
        try{
            lock.writeLock().lock();
            this.myIcon = icons.get(index);
            this.indexImage = index;
        }finally{
            lock.writeLock().unlock();

        }
    }

    public void setMyIcon(String name){
        try{
            lock.writeLock().lock();
            for(int i = 0; i < nameAvatar.length; i++){
                if(name.equals(nameAvatar[i])){
                    this.myIcon = icons.get(i);
                    this.indexImage = i;
                    return;
                }
            }
            this.myIcon = icons.get(0);
            this.indexImage = 0;
        }finally{
            lock.writeLock().unlock();
        }
    }

    public int getMyIconIndex(){
        try{
            lock.readLock().lock();
            return this.indexImage;
        }finally{
            lock.readLock().unlock();

        }
    }

    public String getAvatarName(){
        try{
            lock.readLock().lock();
            return nameAvatar[this.indexImage];
        }finally{
            lock.readLock().unlock();

        }
    }

    @Override
    public boolean equals(Object o){
        try{
            Avatar a = (Avatar) o;
            return this.indexImage == a.indexImage;
        }catch(ClassCastException e){
            return false;
        }
    }
}
