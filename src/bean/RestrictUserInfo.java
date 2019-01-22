package bean;

import entity.Nationality;
import entity.PhoneNumber;
import entity.PrivateData;
import entity.SurfaceAddress;

public class RestrictUserInfo {

    private PrivateData privateData;

    /*Constructor*/

    public RestrictUserInfo(PrivateData priD){

        this.privateData = new PrivateData(priD);
    }

    /*Methods*/

    public PhoneNumber getPhoneNumber(){

        return this.privateData.getPhone();
    }

    public SurfaceAddress getAddress(){

        return this.privateData.getLocalAddress();
    }

    public SurfaceAddress getCityOfBirth(){

        return this.privateData.getCityOfBirth();
    }

    public Nationality getNationality(){

        return this.privateData.getNationality();
    }

    @Override
    public String toString(){

        return this.privateData.toString();
    }

}
