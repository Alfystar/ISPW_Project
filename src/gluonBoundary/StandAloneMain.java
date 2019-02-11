package gluonBoundary;

import entity.Utente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import entity.*;

import java.nio.charset.Charset;
import java.util.GregorianCalendar;

import java.util.Random;
import java.util.Vector;


public class StandAloneMain extends Application{

    private Vector<Utente> users = new Vector<>();
    public static void main(String[] args) throws Exception{

        StandAloneMain stand = new StandAloneMain();
        //todo Inserire inizializzazione e tread vari
        for(int i = 0; i <10 ; i++){
            stand.randomString(10);
        }

        launch(args);
        System.exit(0);
    }

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlSrc/homeStandAlone.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("FERSA StandAlone");
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ImageFile/Icon/Fersa-logo.png")));
        stage.show();
    }

    private Utente randomUser()
    {
        PublicData pubD = new PublicData(new Name("Marta"), new Name("Caggiano"), new TaxCode("cggmrt"), new Nickname("caggy"), new Email("marta.caggiano@hotmail"), new GregorianCalendar(1998, 2, 3), Gender.WOMAN);
        PrivateData priD = new PrivateData(new SurfaceAddress("Alatri casa"), new SurfaceAddress("alatri"), new Nationality("Italiana"), new PhoneNumber("077152345678"));
        Utente us1 = new Utente(pubD, priD, new PW("12345"), new Roles(false, true), new Questions(new String[]{"abaco", "ballerina", "coniglio", "destra"}));
        return us1;
    }

    private String randomString(int len){
        byte[] array = new byte[len]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
        return generatedString;
    }

}
