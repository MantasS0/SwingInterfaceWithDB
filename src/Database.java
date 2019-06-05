import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    protected Connection dbConnection = null; // prisijungimas prie duombazes
    protected PreparedStatement databasePrepareStatement = null; // uzklausu siuntimui

    private String dbUser = "root";
    private String dbPassword = "root";
    private String dbHost = "localhost:3306";
    private String dbName = "uab_nuoma";

    Database() {

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
            dbConnection = DriverManager.getConnection("jdbc:mysql://" + this.dbHost +"/"+ this.dbName +"?useSSL=false", this.dbUser, this.dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}
