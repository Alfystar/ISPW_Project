package entity;
public class SocialStatus extends ModifyDataString {
    private String socialStatus;

    public SocialStatus(String socialStatus){
        this.socialStatus= socialStatus;
    }

    public SocialStatus(){
        this.socialStatus="Non Specificato";
    }

    @Override
    public String get(){
        return this.socialStatus;
    }
    @Override
    public void set(String newSocialStatus){
        this.socialStatus= newSocialStatus;
    }
}
