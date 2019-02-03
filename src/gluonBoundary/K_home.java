package gluonBoundary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class K_home  implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void adminSimulation(ActionEvent event) throws IOException {
        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/adminPane.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void userSimulation(ActionEvent event) throws IOException {
        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/login.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

    @FXML
    public void otherSimulation(ActionEvent event) throws IOException {
        //prepare new scene to replace
        Parent adminParent = FXMLLoader.load(getClass().getResource("fxmlSrc/otherPane.fxml"));
        Scene adminScene = new Scene(adminParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        //set new scene
        window.setScene(adminScene);
    }

}
