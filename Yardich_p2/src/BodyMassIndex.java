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

    public String BMICategory() {
        double score = this.BMIScore();
        if (score <= 18.5) {
            return "Underweight <= 18.5";
        } else if (score <=24.9) {
            return "Normal weight = 18.5–24.9";
        } else if (score <= 29.9) {
            return "Overweight = 25–29.9";
        } else if (score > 29.9){
            return "Obesity = BMI of 30 or greater";
        } else {
            return "Error in function BMICategory";
        }
    }
}
