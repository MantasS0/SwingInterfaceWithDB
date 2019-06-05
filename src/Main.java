import javax.xml.crypto.Data;
import java.sql.*;


public class Main {

    public static void main(String[] args)
    {
        Database.makeDBConnection();

        Car.getData();
        User.getData();


        Car.cars[0].setCarMake("VW Golf");
        Car.cars[0].setUserId(4);

        Car.cars[0].saveData();



    }
}
