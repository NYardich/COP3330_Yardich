import javax.naming.NamingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class TaskList implements ItemList<TaskItem> {
    private ArrayList<TaskItem> items;

    // Constructor
    TaskList() {
        this.items = new ArrayList<>();
    }

    // Add Task Item in Task List
    @Override
    public void addItem(TaskItem item) {
        items.add(item);
    }

    // Delete Task Item in Task List at given index
    @Override
    public void deleteItem(int index) {
        items.remove(index);
    }

    // Edit Task Item at given index with given values
    public void editItem(int index, String title, String description, LocalDate dueDate) throws NamingException {
        items.get(index).setTitle(title);
        items.get(index).setDescription(description);
        items.get(index).setDueDate(dueDate);
    }

    // Return size of Task List
    @Override
    public int size() {
        return items.size();
    }

    // Write Contact List to File
    @Override
    public void write(String filename) {
        try(Formatter output = new Formatter(filename + ".txt")) {
            for(int i = 0; i < this.size(); i++) {
                output.format("%s%n%s%n%s%n%s%n", items.get(i).getTitle(), items.get(i).getDescription(), items.get(i).getDueDate(), items.get(i).isCompleted());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println("Unknown error in File Write: " + e.getMessage());
        }
    }

    // Read Contact List from File
    @Override
    public boolean read(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename + ".txt"));
            while (sc.hasNext()) {
                String title = sc.nextLine();
                String description = sc.nextLine();
                LocalDate dueDate = LocalDate.parse(sc.nextLine());
                boolean completed = Boolean.valueOf(sc.nextLine());
                this.addItem(new TaskItem(title, description, dueDate, completed));
            }
            System.out.println("File Received.");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("The file name was not found in this directory.");
            return false;
        } catch (NamingException e) {
            System.out.println("A parameter in the file has been tampered with.");
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred in file I/O; It's likely that the format in which the data was saved is not supported.");
            return false;
        }
    }

    // Formatted String for list of Task Items
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            ret.append(i + ") " + items.get(i) + "%n");
        }
        return ret.toString();
    }

    // setComplete exists because a TaskItem in a TaskList can have its Completion state set outside of edit

    public void setComplete(int index, boolean completion) { items.get(index).setCompleted(completion); }

    // Simple getters with indexes, allowing Users to find values at indexes without being able to edit them

    public boolean isComplete(int index) {
        return items.get(index).isCompleted();
    }

    public String getTitle(int index) {
        return items.get(index).getTitle();
    }

    public String getDescription(int index) {
        return items.get(index).getDescription();
    }

    public LocalDate getDueDate(int index) {
        return items.get(index).getDueDate();
    }
}
