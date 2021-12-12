package lab5;

public abstract class Vehicle {
    public abstract void NumberOfWheels();
}
class Car extends Vehicle{
//    @Override
    public void NumberOfWheels() {
        System.out.println("car 4 wheel");
    }
}
class Motorcycle extends Vehicle{
//    @Override
    public void NumberOfWheels() {
        System.out.println(" motorcycle 2wheels");
    }
}
class Test02{
    public static void main(String[] args) {
        Car car=new Car();
        Motorcycle motorcycle=new Motorcycle();
        car.NumberOfWheels();
        motorcycle.NumberOfWheels();
    }
}
