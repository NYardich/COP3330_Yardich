public class Sphere extends Shape3D {
    protected double radius;

    Sphere(double radius) {
        this.radius = radius;
    }
    public String getName() {
        return "sphere";
    }

    public double getArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double getVolume() {
        return (4.0/3) * Math.PI * Math.pow(radius, 3);
    }
}
