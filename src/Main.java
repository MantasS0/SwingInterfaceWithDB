public class Main {

    public static void main(String[] args)
    {
        Database.makeDBConnection();
        User.getData();
        Car.getData();


        System.out.println(Car.cars[0].getUser().getEmail());


        Car.cars[0].getUser().setEmail("Naujas@labas.lt");
        Car.cars[0].getUser().saveData();


      //  Car.cars[0].setCarMake("VW Golf");
        //Car.cars[0].setUserId(4);

       // Car.cars[0].getUserId();

     //   Car.cars[0].saveData();



    }
}
