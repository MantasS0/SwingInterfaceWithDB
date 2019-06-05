import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Database {
    private int id;
    private String email;
    private String name;

    public static User[] users = new User[10];

    public static int getUsersCount() {
        return usersCount;
    }

    public static int usersCount = 0;

    public User(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;

        User.usersCount++;

        System.out.println("Sukurtas naujas user objektas: "  + this.email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public void saveData() {

    }

    @Override
    public void deleteData() {

    }

    public static void getData() {
        try {
            String selectQueryStatement = "SELECT * from nuoma_users";
            dbPrepareStatement = dbConnection.prepareStatement(selectQueryStatement);
            ResultSet results = dbPrepareStatement.executeQuery();

            while (results.next()) {
                /* Gauname rezultatus is duombazes ir issaugome i laikinus darbinius kintamuosius */
                int id = results.getInt("id");
                String email = results.getString("email");
                String name =  results.getString("name");

                /* Sukuriame user objekta */
                User.users[User.getUsersCount()] = new User(id, email, name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserById(int id) {
        User temp = null;
        for(User user : User.users ) {
            if(user.id == id) {
                temp = user;
                break;
            }
        }

        return temp;
    }
}
