package gluonBoundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class login extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlSrc/login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Icon/Fersa-logo.png")));
        //primaryStage.setResizable(false);
        //primaryStage.sizeToScene();
        primaryStage.show();

    }
}