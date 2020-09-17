import java.util.Scanner;
public class BodyMassIndex {
    // Attributes
    private double height;
    private double weight;

    // Constructor
    public BodyMassIndex(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    // Methods
    public double BMIScore() {
        return (703 * this.weight) / Math.pow(this.height, 2);
    }

    public void BMICategory() {
        double score = this.BMIScore();
        if (score <= 18.5) {
            System.out.println("Underweight");
        } else if (score <=24.9) {
            System.out.println("Normal weight");
        } else if (score <= 29.9) {
            System.out.println("Overweight");
        } else {
            System.out.println("Obesity");
        }
    }
}
