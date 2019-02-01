package gluonBoundary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
public class K_resetPw {
    private Parent resetPw;

    @FXML
    public void initialize() throws Exception {
        resetPw = FXMLLoader.load(getClass().getResource("fxmlSrc/resetPw.fxml"));
    }
}
