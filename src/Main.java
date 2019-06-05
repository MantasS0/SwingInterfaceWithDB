import javax.xml.crypto.Data;
import java.sql.*;


public class Main {

    public static void main(String[] args)
    {
        Database.makeDBConnection();

        Car.getData();



    }
}
