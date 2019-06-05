import java.sql.ResultSet;
import java.sql.SQLException;

public class Car extends Database {
    private static int carsCount = 0;
    private static Car[] cars = new Car[10];

    private int id;
    private String numberPlate;
    private String carMake;
    private int userId;




    Car(int id, String numberPlate, String carMake) {
        super();
        this.id = id;
        this.numberPlate = numberPlate;
        this.carMake = carMake;
        Car.carsCount++;
        System.out.println("Sukurta nauja masina: " + this.carMake);
    }

    public static int getCarsCount() {
        return carsCount;
    }

    public static void setCarsCount(int carsCount) {
        Car.carsCount = carsCount;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void getData() {
        try {
            String selectQueryStatement = "SELECT * from nuoma_cars";

            databasePrepareStatement = this.dbConnection.prepareStatement(selectQueryStatement);
            ResultSet results = this.databasePrepareStatement.executeQuery();

            while (results.next()) {
                /* Gauname rezultatus is duombazes ir issaugome i laikinus darbinius kintamuosius */
                int id = results.getInt("id");
                String number = results.getString("number_plate");
                String carMake =  results.getString("car_make");

                /* Sukuriame masinos objekta */
                Car.cars[Car.getCarsCount()] = new Car(id, number, carMake);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
