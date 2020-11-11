import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        System.out.printf("Main Menu%n---------%n");
        boolean repeat = true;
        while (repeat) {
            try {
                System.out.printf("1) create a new list%n2) load an existing list%n3) quit%n\t> ");
                int response = input.nextInt();
                repeat = mainMenuDecision(response);
            } catch (InputMismatchException e) {
                System.out.println("Answer must be an integer");
                input.next();
            }
        }
    }

    private static void listOperationMenu(TaskList current) {
        boolean repeat = true;
        while (repeat) {
            System.out.printf("%nList Operation Menu%n---------%n");
            try {
                System.out.printf("1) view the list%n2) add an item%n3) edit an item%n4) remove an item%n5) mark an item as completed%n" +
                        "6) unmark an item as completed%n7) save the current list%n8) quit to the main menu%n\t> ");
                int response = input.nextInt();
                repeat = listOperationMenuDecision(response, current);
            } catch (InputMismatchException e) {
                System.out.println("Answer must be an integer");
                input.next();
            }
        }
    }

    private static boolean mainMenuDecision(int response) {
        switch (response) {
            case 1:
                TaskList here = new TaskList();
                System.out.println("new task list has been created");
                listOperationMenu(here);
                return true;
            case 2:
                return true;
            case 3:
                System.out.println("Goodbye!");
                return false;
            default:
                System.out.println("Answer must be 1, 2, or 3");
                return true;
        }
    }

    private static boolean listOperationMenuDecision(int response, TaskList current) {
        switch (response) {
            case 1:
                current.printList(0);
                return true;
            case 2:
                current.addItem(taskItemPrompt());
                return true;
            case 3:
                current.printList(0);
                taskListEditPrompt(current);
                return true;
            case 4:
                current.printList(0);
                taskListRemovePrompt(current);
                return true;
            case 5:
                current.printList(2);
                completionChange(current, 2);
                return true;
            case 6:
                current.printList(1);
                completionChange(current, 1);
                return true;
            case 7:
                return true;
            case 8:
                System.out.printf("Main Menu%n---------%n");
                return false;
            default:
                System.out.println("Answer must be between 1 and 8");
                return true;
        }
    }

    private static TaskItem taskItemPrompt() {
        while (true) {
            try {
                System.out.print("Task Title: ");
                String title = input.next();
                System.out.print("Task Description: ");
                String description = input.next();
                System.out.print("Task Due  [YYYY-MM-DD]: ");
                LocalDate dueDate = LocalDate.parse(input.next());
                return new TaskItem(title, description, dueDate);
            } catch (InputMismatchException | DateTimeException e) {
                System.out.println("Incorrect input");
                input.next();
            }
        }
    }

    private static void taskListRemovePrompt(TaskList current) {
        try {
            System.out.print("Enter the index of the task you wish to delete: ");
            int index = input.nextInt();
            current.deleteItem(index);
        } catch(InputMismatchException e) {
            System.out.println("Incorrect Input");
            input.next();
        }
    }

    private static void taskListEditPrompt(TaskList current) {
        try {
            System.out.print("Enter the index of the task you wish to edit: ");
            int index = input.nextInt();
            System.out.print("New Task Title: ");
            String title = input.next();
            System.out.print("New Task Description: ");
            String description = input.next();
            System.out.print("New Task Due  [YYYY-MM-DD]: ");
            LocalDate dueDate = LocalDate.parse(input.next());
            current.editItem(index, title, description, dueDate);
        } catch (InputMismatchException | DateTimeException e) {
            System.out.println("Incorrect Input");
            input.next();
        }
    }

    private static void completionChange(TaskList current, int completed) {
        try {
            int choice = 0;
            switch (completed) {
                case 1:
                    System.out.print("Which task will you unmark as completed? ");
                    choice = input.nextInt();
                    if (current.get(choice).isCompleted()) {
                        current.get(choice).setCompleted(false);
                    } else { System.out.println("The selected index is already uncompleted."); }
                    break;
                case 2:
                    System.out.print("Which task will you mark as completed? ");
                    choice = input.nextInt();
                    if (!current.get(choice).isCompleted()) {
                        current.get(choice).setCompleted(true);
                    } else { System.out.println("The selected index is either already completed."); }
                    break;
                default:
                    System.out.println("completionChange: Invalid print type");
            }
        } catch(InputMismatchException e) {
            System.out.println("Incorrect Input");
            input.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist in this list.");
        }
    }
}
