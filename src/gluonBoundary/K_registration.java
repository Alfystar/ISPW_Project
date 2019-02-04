package gluonBoundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class K_registration implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void registerPush(ActionEvent event) throws IOException {
        //todo:should be a validation, but for now...

        Parent userParent = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));
        Scene userScene = new Scene(userParent);

        Stage windows = (Stage)((Node)event.getSource()).getScene().getWindow();
        windows.setScene(userScene);
    }
}
