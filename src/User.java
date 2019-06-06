import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends Database {
    private Integer id;
    private String email;
    private String name;

    public static User[] users = new User[10];

    public static int getUsersCount() {
        return usersCount;
    }

    public static int usersCount = 0;

    public User(String email, String name) {
        this.email = email;
        this.name = name;

        User.usersCount++;

        System.out.println("Sukurtas naujas user objektas: " + this.email);
        this.createData();
    }

    public User(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;

        User.usersCount++;

        System.out.println("Sukurtas naujas user objektas: " + this.email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void saveData() {
        try {
            String insertQueryStatement = "UPDATE `rental_users` SET `email` = ?, `name` = ? WHERE `rental_users`.`id` = ?;";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement);
            dbPrepareStatement.setString(1, this.email);
            dbPrepareStatement.setString(2, this.name);
            dbPrepareStatement.setInt(3, this.id);

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
            String insertQueryStatement = "INSERT INTO `rental_users` (`email`, `name`) VALUES (?, ?)";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement, Statement.RETURN_GENERATED_KEYS);
            dbPrepareStatement.setString(1, this.email);
            dbPrepareStatement.setString(2, this.name);
            dbPrepareStatement.execute();

            try (ResultSet generatedKeys = dbPrepareStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.id;
    }

    @Override
    public void deleteData() {
        try {
            String insertQueryStatement = "DELETE FROM `rental_users` WHERE `rental_users`.`id` = ? ";
            dbPrepareStatement = dbConnection.prepareStatement(insertQueryStatement);
            dbPrepareStatement.setInt(1, this.id);
            dbPrepareStatement.execute();
            for (int i = 0; i < User.users.length; i++) {
                if (User.users[i].id == this.id) {
                    User.users[i] = null;
                    User.usersCount--;
                    sortUserArray();
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void sortUserArray() {
        for (int i = 0; i < User.users.length; i++) {
            if (User.users[i] == null && User.users[i + 1] != null) {
                User.users[i] = User.users[i + 1];
                User.users[i + 1] = null;
            }
        }
    }

    public static void getData() {
        try {
            String selectQueryStatement = "SELECT * from rental_users";
            dbPrepareStatement = dbConnection.prepareStatement(selectQueryStatement);
            ResultSet results = dbPrepareStatement.executeQuery();

            while (results.next()) {
                /* Gauname rezultatus is duombazes ir issaugome i laikinus darbinius kintamuosius */
                int id = results.getInt("id");
                String email = results.getString("email");
                String name = results.getString("name");

                /* Sukuriame user objekta */
                User.users[User.getUsersCount()] = new User(id, email, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserById(int id) {
        User temp = null;
        for (int i = 0; i < User.getUsersCount(); i++) {
            if (User.users[i] != null && User.users[i].id == id) {
                temp = User.users[i];
                break;
            }
        }

        return temp;
    }
}
