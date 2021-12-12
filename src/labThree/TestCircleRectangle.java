package labThree;
//todo 实验6
public class TestCircleRectangle {

    public static void main(String[] args) {
        Object object1 = new TestCircleRectangle().new Circle4(1);
        Object object2 = new TestCircleRectangle().new Rectangle1(1, 1);
        displayObject(object1);
        displayObject(object2);
    }
    public static void displayObject(Object object) {
        if (object instanceof Circle4) {
            System.out.println("The circle area is " +
                    ((Circle4)object).getArea());
            System.out.println("The circle diameter is " +
                    ((Circle4)object).getDiameter());
        }
        else if (object instanceof Rectangle1) {
            System.out.println("The rectangle area is " +
                    ((Rectangle1)object).getArea());
        }
    }
    public  class GeometricObject1 {
        private String color = "white";
        private boolean filled;
        private java.util.Date dateCreated;
        /** Construct a default geometric object */
        public GeometricObject1() {
            dateCreated = new java.util.Date();
        }
        /** Construct a geometric object with the specified color
         * and filled value */
        public GeometricObject1(String Color, boolean filled) {
            dateCreated = new java.util.Date();
            this.color = Color;
            this.filled = filled;
        }
        /** Return color */
        public String getColor() {
            return color;
        }
        /** Set a new color */
        public void setColor(String color) {
            this.color = color;
        }
        /** Return filled. Since filled is boolean,
         its get method is named isFilled */
        public boolean isFilled() {
            return filled;
        }
        /** Set a new filled */
        public void setFilled(boolean filled) {
            this.filled = filled;
        }
        /** Get dateCreated */
        public java.util.Date getDateCreated() {
            return dateCreated;
        }
        /** Return a string representation of this object */
        public String toString() {
            return "created on " + dateCreated + "\ncolor: " + color +
                    " and filled: " + filled;
        }
    }
    public  class Circle4 extends GeometricObject1 {
        private double radius;
        public Circle4() {
        }
        public Circle4(double radius) {
            super();
            this.radius = radius;
        }
        public Circle4(double radius, String color, boolean filled) {
            super(color, filled);
            this.radius = radius;
            //setColor(color);
            //setFilled(filled);
        }
        /** Return radius */
        public double getRadius() {
            return radius;
        }
        /** Set a new radius */
        public void setRadius(double radius) {
            this.radius = radius;
        }
        /** Return area */
        public double getArea() {
            return radius * radius * Math.PI;
        }
        /** Return diameter */
        public double getDiameter() {
            return 2 * radius;
        }
        /** Return perimeter */
        public double getPerimeter() {
            return 2 * radius * Math.PI;
        }
        /* Print the circle info */
        public void printCircle() {
            System.out.println(toString() + "The circle is created " + getDateCreated() +
                    " and the radius is " + radius);
        }
        public String toString() {
            return "Circle WWWW " + getColor() + super.toString();
        }
    }

    public  class Rectangle1 extends GeometricObject1 {
        private double width;
        private double height;
        public Rectangle1() {
        }
        public Rectangle1(double width, double height) {
//            this.width = width;
//            this.height = height;
            setWidth(width);
            setHeight(height);
        }
        public Rectangle1(double width, double height, String color,boolean filled) {
            this.width = width;
            this.height = height;
            setColor(color);
            setFilled(filled);
        }
        /** Return width */
        public double getWidth() {
            return width;
        }
        /** Set a new width */
        public void setWidth(double width) {
            this.width = width;
        }
        /** Return height */
        public double getHeight() {
            return height;
        }
        /** Set a new height */
        public void setHeight(double height) {
            this.height = height;
        }
        /** Return area */
        public double getArea() {
            return width * height;
        }
        /** Return perimeter */
        public double getPerimeter() {
            return 2 * (width + height);
        }
    }
}