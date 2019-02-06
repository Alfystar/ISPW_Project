package externalControl;

import interfaces.UserProfileService;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ChangeDataControl {

    private static ChangeDataControl instance;

    private UserProfileService UsProfInt; // = new Facade();

    public static ChangeDataControl getInstance() {
        if (instance == null)
            instance = new ChangeDataControl();
        return instance;
    }

    private ChangeDataControl() {
    }

    public Boolean changeData() {
        try {
            return TRUE;
        }
        catch(Exception e){
            return FALSE;
        }
    }
}
