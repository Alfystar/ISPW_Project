package gluonBoundary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class K_home {
    private Parent home;


    @FXML
    public void initialize() throws Exception {
        home = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));

    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(new Scene(home));
        primaryStage.setTitle("FERSA StandAlone");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Icon/Fersa-logo.png")));
        primaryStage.show();

    }
}
