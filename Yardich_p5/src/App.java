import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        applicationChoice();
    }

    public static void applicationChoice() {
        boolean repeat = true;
        while(repeat) {

            System.out.printf("%n---------%nSelect Your Application%n---------%n");
            try {
                System.out.printf("1) task list%n2) contact list%n3) quit%n\t> ");
                int response = input.nextInt();
                input.nextLine();
                repeat = applicationChoiceDecision(response);
            } catch (InputMismatchException e) {
                System.out.println("Answer must be an integer.");
                input.nextLine();
            }
        }
    }
    public static boolean applicationChoiceDecision(int response) {
        switch(response) {
            case 1:
                TaskApp.mainMenu();
                return true;
            case 2:

                return true;
            case 3:
                System.out.println("Goodbye!");
                return false;
            default:
                System.out.println("Answer must be 1, 2, or 3.");
                return true;
        }
    }
}