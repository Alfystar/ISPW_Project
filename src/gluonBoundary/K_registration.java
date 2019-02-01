package gluonBoundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class K_registration {
    private Parent registration;

    @FXML
    public void initialize() throws Exception {
        registration = FXMLLoader.load(getClass().getResource("fxmlSrc/registration.fxml"));
    }
}
