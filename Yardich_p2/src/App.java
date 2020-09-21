import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    private static boolean moreInput() {
        Scanner input = new Scanner(System.in);
        String response;

        System.out.print("Enter Y to continue or N to stop:\n\t> ");
        response = input.next();
        switch(response) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                System.out.println("Answer should be Y or N");
        }
        return false;
    }

    public static double getUserHeight() {
        Scanner input = new Scanner(System.in);
        double height;
        while(true) {
            System.out.print("Enter Height in Inches:\n\t> ");
            height = input.nextDouble();
            input.nextLine();
            if (height > 0) {
                return height;
            } else {
                System.out.println("Height must be positive.");
            }
        }
    }

    public static double getUserWeight() {
        Scanner input = new Scanner(System.in);
        double weight;
        while(true) {
            System.out.print("Enter Weight in Pounds:\n\t> ");
            weight = input.nextDouble();
            input.nextLine();
            if (weight > 0) {
                return weight;
            } else {
                System.out.println("Weight must be positive.");
            }
        }
    }

    public static void displayBmiInfo(BodyMassIndex bmi) {
        System.out.printf("BMI: %.2f\nCategory: %s\n", bmi.BMIScore(), bmi.BMICategory());
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmi) {
        double total = getAverageBMI(bmi);
        System.out.printf("Average of all BMIs: %.2f\n", total);
    }

    public static double getAverageBMI(ArrayList<BodyMassIndex> bmi) {
        double total = 0;
        for (BodyMassIndex user : bmi) {
            total += user.BMIScore();
        }
        return total / bmi.size();
    }
}
