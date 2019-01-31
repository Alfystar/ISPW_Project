package gluonBoundary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
public class K_resetPw {
    private Parent resetPw;

    @FXML
    public void initialize() throws Exception {
        resetPw = FXMLLoader.load(getClass().getResource("fxmlSrc/resetPw.fxml"));
    }
}
