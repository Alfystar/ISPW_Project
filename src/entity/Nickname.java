package entity;

public class Nickname{
    private String nickname;

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
        return this.nickname;
    }

    public void set(String newNickname){
        this.nickname = newNickname;
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
