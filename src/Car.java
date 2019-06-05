import java.sql.ResultSet;
import java.sql.SQLException;

public class Car extends Database {
    private static int carsCount = 0;
    public static Car[] cars = new Car[10];

    private int id;
    private String numberPlate;



    private String carMake;
    private int userId;
    private User user;




    Car(int id, String numberPlate, String carMake) {
        super();
        this.id = id;
        this.numberPlate = numberPlate;
        this.carMake = carMake;
        Car.carsCount++;
        System.out.println("Sukurta nauja masina: " + this.carMake);
    }

    Car(int id, String numberPlate, String carMake, User user) {
        super();
        this.id = id;
        this.numberPlate = numberPlate;
        this.carMake = carMake;
        this.user = user;
        Car.carsCount++;
        System.out.println("Sukurta nauja masina: " + this.carMake);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static int getCarsCount() {
        return carsCount;
    }

    public static void setCarsCount(int carsCount) {
        Car.carsCount = carsCount;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
//        this.saveData();
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void saveData() {
        try {
            String insertQueryStatement = "UPDATE `nuoma_cars` SET `car_make` = ?, `number_plate` = ?, `user_id` = ? WHERE `nuoma_cars`.`id` = ?;";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement);
            dbPrepareStatement.setString(1, this.carMake);
            dbPrepareStatement.setString(2, this.numberPlate);
            dbPrepareStatement.setInt(3, this.userId);
            dbPrepareStatement.setInt(4, this.id);

            // execute insert SQL statement
            dbPrepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.print("Kazkas blogai");
            e.printStackTrace();
        }
    }

    public void deleteData() {
        // cia rasysime veliau
    }

    public static void getData() {


        try {
            String selectQueryStatement = "SELECT * from nuoma_cars";
            dbPrepareStatement = dbConnection.prepareStatement(selectQueryStatement);
            ResultSet results = dbPrepareStatement.executeQuery();

            while (results.next()) {
                /* Gauname rezultatus is duombazes ir issaugome i laikinus darbinius kintamuosius */
                int id = results.getInt("id");
                String number = results.getString("number_plate");
                String carMake =  results.getString("car_make");
                Integer userId =  results.getInt("user_id");

                /* Sukuriame masinos objekta */
                if(userId != null) {
                    User temp = User.getUserById(userId);
                    Car.cars[Car.getCarsCount()] = new Car(id, number, carMake, User.getUserById(userId));

                } else {
                    Car.cars[Car.getCarsCount()] = new Car(id, number, carMake);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
