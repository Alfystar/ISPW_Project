package gluonBoundary;

import DAO.DAOClass;
import DAO.DaemonDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class StandAloneMain extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("FERSA StandAlone");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ImageFile/Icon/Fersa-logo.png")));
        stage.show();
    }

    public static void main(String[] args) throws Exception{

        //todo Inserire inizializzazione e tread vari
        //DAOClass dao= new DAOClass("169.254.224.65");
        //DaemonDAO daemonDAO = DaemonDAO.getInstance();

        launch(args);
        System.exit(0);
    }

}
