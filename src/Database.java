import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    protected static Connection dbConnection = null; // prisijungimas prie duombazes
    protected static PreparedStatement databasePrepareStatement = null; // uzklausu siuntimui

    private static String dbUser = "root";
    private static String dbPassword = "root";
    private static String dbHost = "localhost:3306";
    private static String dbName = "uab_nuoma";

    public static void makeDBConnection() {

        /* Patikriname ar irasytas JDBC driveris darbui su mysql */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        /*
         * Patikriname ar pavyksta prisijungti prie duombazes ir sukuriame
         * duombazes prisijungimo kintamuosius
         */
        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            Database.dbConnection = DriverManager.getConnection("jdbc:mysql://" + dbHost +"/"+ dbName +"?useSSL=false", dbUser, dbPassword);
            System.out.println("Database connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    Database() {

    }
}
