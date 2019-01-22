package entity;
public class PrivateData {
    private SurfaceAddress address;
    private SurfaceAddress cityOfBirth;
    private Nationality nat;
    private PhoneNumber phone;

    //Costruttore con tutti i possibili attributi di PrivateData
    public PrivateData(SurfaceAddress address, SurfaceAddress cityOfBirth, Nationality nat, PhoneNumber phone){
        this.address= new SurfaceAddress(address);
        this.cityOfBirth= new SurfaceAddress(cityOfBirth);
        this.nat= new Nationality(nat);
        this.phone= new PhoneNumber(phone);
    }

    //Costruttore senza parametri; non è richiesto nessun PrivateData per la registrazione(UserInfoRegister)
    public PrivateData(){

    }

    //Costruttore per copiare i dati di un PrivateData in un'altra istanza di PrivateData
    public PrivateData(PrivateData prD){
        this.address= new SurfaceAddress(address);
        this.cityOfBirth= new SurfaceAddress(cityOfBirth);
        this.nat= new Nationality(nat);
        this.phone= new PhoneNumber(phone);
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

}
