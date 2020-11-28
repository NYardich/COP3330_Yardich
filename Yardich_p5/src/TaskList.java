import javax.naming.NamingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class TaskList implements ItemList<TaskItem> {
    private ArrayList<TaskItem> items;

    TaskList() {
        this.items = new ArrayList<>();
    }

    // 0: print everything, 1: print completed, 2: print uncompleted
    public void printList(int completed) {
        System.out.printf("%nCurrent Tasks%n-------------%n");
        switch(completed) {
            case 0:
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(i + ") " + items.get(i));
                }
                break;
            case 1:
                for (int i = 0; i < items.size(); i++) {
                    if(items.get(i).isCompleted()) {
                        System.out.println(i + ") " + items.get(i));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < items.size(); i++) {
                    if(!items.get(i).isCompleted()) {
                        System.out.println(i + ") " + items.get(i));
                    }
                }
                break;
            default:
                System.out.println("Print Mode Undefined");
        }
    }

    @Override
    public void addItem(TaskItem item) {
        items.add(item);
    }

    @Override
    public void deleteItem(int index) {
        items.remove(index);
    }

    public void editItem(int index, String title, String description, LocalDate dueDate) throws NamingException {
        items.get(index).setTitle(title);
        items.get(index).setDescription(description);
        items.get(index).setDueDate(dueDate);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            ret.append(i + ") " + items.get(i));
        }
        return ret.toString();
    }

    @Override
    public void write(String filename) {
        try(Formatter output = new Formatter(filename + ".txt")) {
            for(int i = 0; i < this.size(); i++) {
                output.format("%s%n%s%n%s%n%s%n", items.get(i).getTitle(), items.get(i).getDescription(), items.get(i).getDueDate(), items.get(i).isCompleted());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("Unknown error in File Write");
            e.printStackTrace();
        }
    }

    @Override
    public void read(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename + ".txt"));
        while (sc.hasNext()) {
            String title = sc.nextLine();
            String description = sc.nextLine();
            LocalDate dueDate = LocalDate.parse(sc.nextLine());
            boolean completed = Boolean.valueOf(sc.nextLine());
            this.addItem(new TaskItem(title, description, dueDate, completed));
        }
        System.out.printf("File Received%n");
    }

    public void makeComplete(int index) {
        items.get(index).setCompleted(true);
    }

    public void makeIncomplete(int index) {
        items.get(index).setCompleted(false);
    }

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
