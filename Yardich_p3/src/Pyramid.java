public class Pyramid extends Shape3D{
    protected double length;
    protected double width;
    protected double height;

    Pyramid(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return "pyramid";
    }

    public double getArea() {
        return this.length * this.width + (this.length * Math.sqrt(Math.pow((this.width / 2), 2) + Math.pow(this.height, 2))) + (this.width * Math.sqrt(Math.pow((this.length / 2), 2) + Math.pow(this.height, 2)));
    }

    public double getVolume() {
        return (1/3.0) * (this.length * this.width) * this.height;
    }
}
