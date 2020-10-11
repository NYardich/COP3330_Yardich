public class Cube extends Shape3D {
    protected double edge;

    Cube(double length) {
        this.edge= length;
    }
    public String getName() {
        return "cube";
    }

    public double getArea() {
        return 6 * Math.pow(this.edge, 2);
    }

    public double getVolume() {
        return Math.pow(this.edge, 3);
    }
}
