package bean;

import entity.PrivateData;
import entity.PublicData;

public class FactoryInfo {

    public BasicUserInfo createBasic(PublicData pubD){

        return new BasicUserInfo(pubD);
    }

    public RestrictUserInfo createRestrict(PrivateData priD){

        return new RestrictUserInfo(priD);
    }

}
