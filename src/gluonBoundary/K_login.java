package gluonBoundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class K_login {

    @FXML
    private TextField nickField;
    @FXML
    private PasswordField pwField;

    private Parent home;
    private Parent login,registration,resetPw,user;
    private Parent admin,other;

    @FXML
    public void initialize() throws Exception {
        home = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));
        login = FXMLLoader.load(getClass().getResource("fxmlSrc/login.fxml"));
        registration = FXMLLoader.load(getClass().getResource("fxmlSrc/registration.fxml"));
        resetPw = FXMLLoader.load(getClass().getResource("fxmlSrc/resetPw.fxml"));
        user = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));
        admin = FXMLLoader.load(getClass().getResource("fxmlSrc/adminPane.fxml"));
        other = FXMLLoader.load(getClass().getResource("fxmlSrc/otherPane.fxml"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setScene(new Scene(home));
        primaryStage.setTitle("FERSA StandAlone");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Icon/Fersa-logo.png")));
        primaryStage.show();

    }
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
