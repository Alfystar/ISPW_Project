package gluonBoundary;

import javafx.fxml.FXML;

import javax.swing.*;
import javafx.scene.control.*;
//import java.awt.*;

public class ControllerLogin {

    private int istance=0;
    private static int staticIstance=0;

    @FXML
    private TextField nickField;
    @FXML
    private PasswordField pwField;


    @FXML
    public void loginPush()
    {
        System.out.println(nickField.getText());
        System.out.println(pwField.getText());
        staticIstance++;
        istance++;
        System.out.println("istance:"+istance+" static istance:"+staticIstance);
        try {
            Thread.sleep(5000);
            System.out.println("end of : istance:"+istance+" static istance:"+staticIstance);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    public void registerPush()
    {
        System.out.println("register");
    }
}
