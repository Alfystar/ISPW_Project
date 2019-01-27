package gluonBoundary;

import javafx.fxml.FXML;

import java.awt.*;

public class ControllerLogin {

    @FXML
    private TextField nickField;
    @FXML
    private TextField pwField;


    public void loginPush()
    {
        System.out.println(nickField.getText());
    }

    public void registerPush()
    {
        System.out.println("register");
    }
}
