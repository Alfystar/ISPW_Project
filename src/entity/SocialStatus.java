package entity;


public class SocialStatus extends ModifyDataString{
    private String socialStatus;


    public SocialStatus(String socialStatus){
        this.socialStatus = socialStatus;
    }

    public SocialStatus(){
        this.socialStatus = "Non Specificato";
    }

    public SocialStatus(SocialStatus socialStatus){
        this.socialStatus = socialStatus.get();
    }

    @Override
    public String get(){

        try{
            lock.readLock().lock();
            return this.socialStatus;
        }finally{
            lock.readLock().unlock();
        }
    }

    @Override
    public void set(String newSocialStatus){
        try{
            lock.writeLock().lock();
            this.socialStatus = newSocialStatus;
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.socialStatus.equals(((SocialStatus) o).socialStatus);
        }catch(ClassCastException e){
            return false;
        }
    }
}
