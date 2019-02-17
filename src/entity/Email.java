package entity;


public class Email extends ModifyDataString{
    private String email;

    public Email(String email){
        this.email = email.toLowerCase().replaceAll("\\s+", "");
    }

    public Email(){
        this.email = "";
    }

    public Email(Email email){
        this.email = email.get();
    }

    @Override
    public String get(){
        try{
            lock.readLock().lock();
            return this.email;
        }finally{
            lock.readLock().unlock();
        }
    }

    @Override
    public void set(String newEmail){

        try{
            lock.writeLock().lock();
            this.email = newEmail.toLowerCase().replaceAll("\\s+", "");;
        }finally{
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean equals(Object o){
        try{
            return email.equals(((Email) o).email);
        }catch(ClassCastException e){
            return false;
        }
    }
}
