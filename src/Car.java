import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Car extends Database {
    private static int carsCount = 0;
    public static Car[] cars = new Car[100];

    private int id;
    private String numberPlate;


    private String carMake;
    private int userId;
    private User user;

    Car(String numberPlate, String carMake) {
        super();
        this.numberPlate = numberPlate;
        this.carMake = carMake;
        Car.carsCount++;
        System.out.println("Sukurta nauja masina: " + this.carMake);
        this.createData();
    }

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
            String insertQueryStatement = "UPDATE `rental_cars` SET `car_make` = ?, `number_plate` = ?, `user_id` = ? WHERE `rental_cars`.`id` = ?;";
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

    @Override
    protected int createData() {
        try {
            String insertQueryStatement = "INSERT INTO `rental_cars` (`car_make`, `number_plate`) VALUES (?, ?)";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement, Statement.RETURN_GENERATED_KEYS);
            dbPrepareStatement.setString(1, this.carMake);
            dbPrepareStatement.setString(2, this.numberPlate);
            dbPrepareStatement.execute();

            try (ResultSet generatedKeys = dbPrepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating car failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.id;
    }

    private int insertData() {
        try {
            String insertQueryStatement = "INSERT INTO `rental_cars` (`car_make`, `number_plate`) VALUES (?, ?)";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement);
            dbPrepareStatement.setString(1, this.carMake);
            dbPrepareStatement.setString(2, this.numberPlate);
            dbPrepareStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void deleteData() {
        try {
            String insertQueryStatement = "DELETE FROM `rental_cars` WHERE `rental_cars`.`id` = ? ";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement);
            dbPrepareStatement.setInt(1, this.id);
            dbPrepareStatement.execute();
            int deletedIndex = -1;
            for (int i = 0; i < Car.getCarsCount(); i++) {
                if (Car.cars[i].id == this.id) {
                    Car.cars[i] = null;
                    deletedIndex = i;
                    Car.sortCarsArray(deletedIndex);
                    Car.carsCount--;
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void sortCarsArray(int deletedIndex) {
        for (int i = deletedIndex; i < Car.getCarsCount() - 1; i++) {
            Car.cars[i] = Car.cars[i + 1];
//            if (Car.cars[i] == null && Car.cars[i + 1] != null) {
//                Car.cars[i] = Car.cars[i + 1];
//                Car.cars[i + 1] = null;
//            }
        }
        Car.cars[Car.getCarsCount() - 1] = null;
    }


    public static void getData() {


        try {
            String selectQueryStatement = "SELECT * from rental_cars";
            dbPrepareStatement = dbConnection.prepareStatement(selectQueryStatement);
            ResultSet results = dbPrepareStatement.executeQuery();

            while (results.next()) {
                /* Gauname rezultatus is duombazes ir issaugome i laikinus darbinius kintamuosius */
                int id = results.getInt("id");
                String number = results.getString("number_plate");
                String carMake = results.getString("car_make");
                Integer userId = results.getInt("user_id");

                /* Sukuriame masinos objekta */
                if (userId != null) {
                    User temp = User.getUserById(userId);
                    Car.cars[Car.getCarsCount()] = new Car(id, number, carMake, temp);

                } else {
                    Car.cars[Car.getCarsCount()] = new Car(id, number, carMake);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
