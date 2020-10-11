public class Triangle extends Shape2D {
    protected double width;
    protected double height;

    Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public String getName() {
        return "triangle";
    }
    public double getArea() {
        return (0.5 * this.width * this.height);
    }
}
