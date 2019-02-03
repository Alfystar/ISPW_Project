package gluonBoundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class K_login implements Initializable {

    @FXML
    private TextField nickField;
    @FXML
    private PasswordField pwField;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void loginPush(ActionEvent event) throws IOException {
        System.out.println(nickField.getText());
        System.out.println(pwField.getText());
        //todo: should be a validation, but for now...

        Parent userParent = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));
        Scene userScene = new Scene(userParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(userScene);
    }

    @FXML
    public void registerPush(ActionEvent event) throws IOException{

        Parent registerParent = FXMLLoader.load(getClass().getResource("fxmlSrc/registration.fxml"));
        Scene registerScene = new Scene(registerParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(registerScene);
    }

    @FXML
    public void forgottenPush(ActionEvent event) throws IOException{

        Parent resetPwParent = FXMLLoader.load(getClass().getResource("fxmlSrc/resetPw.fxml"));
        Scene resetPwScene = new Scene(resetPwParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(resetPwScene);
    }
}
