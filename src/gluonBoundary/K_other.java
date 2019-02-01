package gluonBoundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class K_other {

    private Parent other;

    @FXML
    public void initialize() throws Exception {
        other = FXMLLoader.load(getClass().getResource("fxmlSrc/otherPane.fxml"));
    }
}
