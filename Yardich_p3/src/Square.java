public class Square extends Shape2D {
    protected double length;

    Square(double length) {
        this.length = length;
    }

    public String getName() {
        return "square";
    }
    public double getArea() {
        return Math.pow(this.length, 2);
    }
}
