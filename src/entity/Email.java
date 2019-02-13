package entity;

public class Email extends ModifyDataString{
    private String email;

    public Email(String email){
        this.email = email.toLowerCase().replaceAll("\\s+","");
    }

    public Email(){
        this.email = "";
    }

    public Email(Email email){
        this.email = email.get();
    }

    @Override
    public String get(){
        return this.email;
    }

    @Override
    public void set(String newEmail){
        this.email = newEmail;
    }
}
