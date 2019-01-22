package entity;

public class Email extends ModifyDataString {
    private String email;

    public Email(String email){
        this.email= email;
    }
    public Email(){
        this.email= "";
    }

    @Override
    public String get(){
        return this.email;
    }
    @Override
    public void set(String newEmail){
        this.email= newEmail;
    }
}
