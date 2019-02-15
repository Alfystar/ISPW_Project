package entity;

public class PW{
    private String pw;

    public PW(String pw){
        this.pw = pw;
    }

    public PW(PW pw){
        this.pw = pw.getPw();
    }

    public String getPw(){
        return this.pw = pw;
    }

    public void setPw(String newPw){
        this.pw = newPw;
    }

    @Override
    public boolean equals(Object o){
        try{
            return this.pw.equals(((PW) o).pw);
        }catch(ClassCastException e){
            return false;
        }
    }
}
