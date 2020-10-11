public class Circle extends Shape2D{
    protected double radius;

    Circle(double radius) {
        this.radius = radius;
    }
    public String getName() {
        return "circle";
    }
    public double getArea() {
        return (Math.PI * Math.pow(this.radius, 2));
    }
}
