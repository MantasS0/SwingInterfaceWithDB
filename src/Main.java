import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        Database.makeDBConnection();
        User.getData();
        Car.getData();

        /* Sukuriame frame objekta ir parodome varotojui */
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);








//        System.out.println();
//        for (int i=0;i<Car.getCarsCount();i++){
//            System.out.println(Car.cars[i].getCarMake());
//        }
//
//        System.out.println();
//        for (int i=0;i<User.getUsersCount();i++){
//            System.out.println(User.users[i].getEmail());
//        }




    }
}
