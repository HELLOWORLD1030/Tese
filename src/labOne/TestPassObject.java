package labOne;
//todo 实验五
public class TestPassObject {
    /** Main method */
    public static void main(String[] args) {
// Create a Circle object with radius 1
        Circle3 myCircle = new Circle3(1);
// Print areasa for radius 1, 2, 3, 4, and 5.
        int n = 5;
        printAreas(myCircle, n);
// See myCircle.radius and times
        System.out.println("\n" + "Radius is " + myCircle.getRadius());
        System.out.println("n is " + n);
    }
    /** Print a table of areas for radius */
    public static void printAreas(Circle3 c, int times) {
        System.out.println("Radius \t\tArea");
        while (times >= 1) {
            System.out.println(c.getRadius() + "\t\t" + c.getArea());
            c.setRadius(c.getRadius() + 1);
            times--;
        }
    }
    public static class Circle3 {
        /** The radius of the circle */
        private double radius = 1;
        /** The number of the objects created */
        private static int numberOfObjects = 0;
        /** Construct a circle with radius 1 */
        public Circle3() {
            numberOfObjects++;
        }
        /** Construct a circle with a specified radius */
        public Circle3(double newRadius) {
            radius = newRadius;
            numberOfObjects++;
        }
        /** Return radius */
        public double getRadius() {
            return radius;
        }
        /** Set a new radius */
        public void setRadius(double newRadius) {
            radius = (newRadius >= 0) ? newRadius : 0;
        }
        /** Return numberOfObjects */
        public static int getNumberOfObjects() {
            return numberOfObjects;
        }
        /** Return the area of this circle */
        public double getArea() {
            return radius * radius * Math.PI;
        }
    }
}
