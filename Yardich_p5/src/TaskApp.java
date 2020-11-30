import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class TaskApp extends ListApplication<TaskList, TaskItem> {

    // Starting, public menu to access other methods.
    public void mainMenu() {
        boolean repeat = true;
        while (repeat) {
            System.out.printf("Main Menu%n---------%n");
            try {
                System.out.printf("1) create a new list%n2) load an existing list%n3) quit%n\t> ");
                int response = input.nextInt();
                input.nextLine();
                repeat = mainMenuDecision(response);
            } catch (InputMismatchException e) {
                System.out.println("Answer must be an integer");
                input.nextLine();
            }
        }
    }

    // Menu to access and modify a TaskList
    protected void listOperationMenu(TaskList current) {
        boolean repeat = true;
        while (repeat) {
            System.out.printf("%nList Operation Menu%n---------%n");
            try {
                System.out.printf("1) view the list%n2) add an item%n3) edit an item%n4) remove an item%n5) mark an item as completed%n" +
                        "6) unmark an item as completed%n7) save the current list%n8) quit to the main menu%n\t> ");
                int response = input.nextInt();
                input.nextLine();
                repeat = listOperationMenuDecision(response, current);
            } catch (InputMismatchException e) {
                System.out.println("Answer must be an integer");
                input.nextLine();
            }
        }
    }

    // Deals with response to Main Menu Prompt
    protected boolean mainMenuDecision(int response) {
        switch (response) {
            case 1:
                TaskList here = new TaskList();
                System.out.println("new task list has been created");
                listOperationMenu(here);
                return true;
            case 2:
                try {
                    TaskList file = readFile();
                    listOperationMenu(file);
                }
                catch (Exception e) {
                    System.out.println("File Read Failed.");
                } finally {
                    return true;
                }
            case 3:
                System.out.println("Exiting Task List Creation...");
                return false;
            default:
                System.out.println("Answer must be 1, 2, or 3");
                return true;
        }
    }

    // Deals with response to List Operation Menu Prompt
    protected boolean listOperationMenuDecision(int response, TaskList current) {
        switch (response) {
            case 1:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                return true;
            case 2:
                current.addItem(addPrompt());
                return true;
            case 3:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                editPrompt(current);
                return true;
            case 4:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                removePrompt(current);
                return true;
            case 5:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                completeItem(current);
                return true;
            case 6:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                uncompleteItem(current);
                return true;
            case 7:
                writeToFile(current);
                return true;
            case 8:
                System.out.printf("Main Menu%n---------%n");
                return false;
            default:
                System.out.println("Answer must be between 1 and 8");
                return true;
        }
    }

    // Prompts user to enter fields for new task item, then attempts to create that item
    protected TaskItem addPrompt() {
        while (true) {
            try {
                System.out.print("Task Title: ");
                String title = input.nextLine();
                System.out.print("Task Description: ");
                String description = input.nextLine();
                System.out.print("Task Due  [YYYY-MM-DD]: ");
                LocalDate dueDate = LocalDate.parse(input.nextLine());

                return new TaskItem(title, description, dueDate);
            } catch (InputMismatchException e) {
                System.out.println("Incorrect Input type: Task was not created.");
                input.nextLine();
            } catch (DateTimeException e) {
                System.out.println("Invalid Date: Task was not created.");
            }  catch (Exception e) {
                System.out.println(e.getMessage() + ": Task was not created.");
            }
        }
    }

    // Prompts user on which task to remove, then attempts to remove that task
    protected void removePrompt(TaskList current) {
        try {
            System.out.print("Enter the index of the task you wish to delete: ");
            int index = input.nextInt();

            current.deleteItem(index);
        } catch(InputMismatchException e) {
            System.out.println("Index must be an integer");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Index does not exist");
        } finally {
            input.nextLine();
        }
    }

    // Prompts user on which task to edit, asks for fields for that task, then attempts to edit it
    protected void editPrompt(TaskList current) {
        try {
            System.out.print("Enter the index of the task you wish to edit: ");
            int index = input.nextInt();
            input.nextLine();
            System.out.print("New Task Title: ");
            String title = input.nextLine();
            System.out.print("New Task Description: ");
            String description = input.nextLine();
            System.out.print("New Task Due  [YYYY-MM-DD]: ");
            LocalDate dueDate = LocalDate.parse(input.nextLine());

            current.editItem(index, title, description, dueDate);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect Input type: Task was not edited.");
            input.nextLine();
        } catch (DateTimeException e) {
            System.out.println("Invalid Date: Task was not edited.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist in this list: Task was not edited.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": Task was not edited.");
        }
    }

    // Attempts to mark the item at the index as completed
    protected void completeItem(TaskList current) {
        try {
            System.out.print("Which task will you mark as completed? ");
            int choice = input.nextInt();
            input.nextLine();
            if (!current.isComplete(choice)) {
                current.setComplete(choice, true);
            } else {
                System.out.println("The selected index is already completed.");
            }
        } catch(InputMismatchException e) {
            System.out.println("Index must be an integer.");
            input.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist in this list.");
        }
    }

    // Attempts to mark the item at the index as uncompleted
    protected void uncompleteItem(TaskList current) {
        try {
            System.out.print("Which task will you unmark as completed? ");
            int choice = input.nextInt();
            input.nextLine();
            if (current.isComplete(choice)) {
                current.setComplete(choice, false);
            } else {
                System.out.println("The selected index is already uncompleted.");
            }
        } catch(InputMismatchException e) {
            System.out.println("Index must be an integer.");
            input.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist in this list.");
        }
    }

    // Prompts user on filename to write to, then attempts to write a TaskList to that file
    protected void writeToFile(TaskList current) {
        System.out.printf("What is your desired filename? (No need for file extension)%n\t> ");
        String filename = input.nextLine();
       current.write(filename);
    }

    // Prompts user on filename to read from, then attempts to create a TaskList from that file
    protected TaskList readFile() throws Exception {
        System.out.printf("What is the name of your file? Make sure it is .txt and within this directory (No need for file extension)%n\t> ");
        String filename = input.nextLine();
        TaskList ret = new TaskList();

        // read returns a boolean indicating if the file read was successful or not
        if(ret.read(filename)) {
            return ret;
        } else {
            // This exception indicates to the caller that it will not be receiving a Task List from the file
            throw new Exception("File Read Failed.");
        }
    }
}
