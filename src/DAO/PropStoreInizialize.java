package DAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropStoreInizialize
{

    public static void main( String[] args )
    {
        Properties prop = new Properties();
        String confFile = "config.properties";

        try {
            //set the properties value
            prop.setProperty("dbHost", "localhost");
            prop.setProperty("dbuser", "root");
            prop.setProperty("dbpassword", "0000");

            //save properties to project root folder
            prop.store(new FileOutputStream(confFile), null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
