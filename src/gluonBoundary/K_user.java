package gluonBoundary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
public class K_user {
    private Parent user;

    @FXML
    public void initialize() throws Exception {
        user = FXMLLoader.load(getClass().getResource("fxmlSrc/userPane.fxml"));
    }
}
