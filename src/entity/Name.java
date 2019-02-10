package entity;

public class Name{
    private String name;

    public Name(String name){
        this.name = name;
    }

    public Name(){
        this.name = "";
    }

    public Name(Name name){
        this.name = name.get();
    }

    public String get(){
        return this.name;
    }

    public void set(String newName){
        this.name = newName;
    }
}
