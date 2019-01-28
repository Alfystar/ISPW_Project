package gluonBoundary;

import javafx.fxml.FXML;

import javax.swing.*;
import javafx.scene.control.*;
//import java.awt.*;

public class ControllerLogin {

    @FXML
    private TextField nickField;
    @FXML
    private PasswordField pwField;


    @FXML
    public void loginPush()
    {
        System.out.println(nickField.getText());
        System.out.println(pwField.getText());
    }

    @FXML
    public void registerPush()
    {
        System.out.println("register");
    }
}
