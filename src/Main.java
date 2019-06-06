public class Main {

    public static void main(String[] args)
    {
        Database.makeDBConnection();
        User.getData();
        Car.getData();
//        User.users[User.getUsersCount()] = new User("naujas@gmail.com","vartotojas");

//        Car.cars[Car.getCarsCount()] = new Car("HMT 965", "Toyota");

//        System.out.println(Car.cars[0].getUser().getEmail());


//        Car.cars[0].getUser().setEmail("Naujas@labas.lt");
//        Car.cars[0].getUser().saveData();
        Car.cars[0].deleteData();

        System.out.println();
        for (int i=0;i<Car.getCarsCount();i++){
            System.out.println(Car.cars[i].getCarMake());
        }

        System.out.println();
        for (int i=0;i<User.getUsersCount();i++){
            System.out.println(User.users[i].getEmail());
        }

//        Car car1 = new Car("HMT 965", "Mercedes benz");
//        car1.createData();
//        car1.deleteData();

      //  Car.cars[0].setCarMake("VW Golf");
        //Car.cars[0].setUserId(4);

       // Car.cars[0].getUserId();

     //   Car.cars[0].saveData();



    }
}
