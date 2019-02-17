package entity;

import bean.PrototypeData;

public class RestrictUserInfo implements PrototypeData{
    private SurfaceAddress address;
    private SurfaceAddress cityOfBirth;
    private Nationality nat;
    private PhoneNumber phone;

    //Costruttore con tutti i possibili attributi di RestrictUserInfo
    public RestrictUserInfo(SurfaceAddress address, SurfaceAddress cityOfBirth, Nationality nat, PhoneNumber phone){
        this.address = new SurfaceAddress(address);
        this.cityOfBirth = new SurfaceAddress(cityOfBirth);
        this.nat = new Nationality(nat);
        this.phone = new PhoneNumber(phone);
    }

    //Costruttore senza parametri; non è richiesto nessun RestrictUserInfo per la registrazione(UserInfoRegister)
    public RestrictUserInfo(){
        this.address = new SurfaceAddress();
        this.cityOfBirth = new SurfaceAddress();
        this.nat = new Nationality();
        this.phone = new PhoneNumber();

    }

    //Costruttore per copiare i dati di un RestrictUserInfo in un'altra istanza di RestrictUserInfo
    private RestrictUserInfo(RestrictUserInfo prD){
        this.address = new SurfaceAddress(prD.getLocalAddress());
        this.cityOfBirth = new SurfaceAddress(prD.getCityOfBirth());
        this.nat = new Nationality(prD.getNationality());
        this.phone = new PhoneNumber(prD.getPhone());
    }

    public SurfaceAddress getLocalAddress(){
        return this.address;
    }

    public SurfaceAddress getCityOfBirth(){
        return this.cityOfBirth;
    }

    public Nationality getNationality(){
        return this.nat;
    }

    public PhoneNumber getPhone(){
        return this.phone;
    }

    @Override
    public String toString(){
        String out;
        out = "My address:" + this.address.get() + "\n";
        out += "My cityOfBirth:" + this.cityOfBirth.get() + "\n";
        out += "My Nationality:" + this.nat.get() + "\n";
        out += "My PhoneNumber:" + this.phone.get() + "\n";

        return out;

    }

    @Override
    public boolean equals(Object o){
        try{
            RestrictUserInfo p = (RestrictUserInfo) o;
            return (this.address.equals(p.address) && this.cityOfBirth.equals(p.cityOfBirth) &&
                    this.nat.equals(p.nat) && this.phone.equals(p.phone));
        }catch(ClassCastException e){
            return false;
        }
    }

    @Override
    public Object clone(){
        return new RestrictUserInfo(this);
    }
}
