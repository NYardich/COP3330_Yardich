import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<TaskItem> items;

    TaskList() {
        this.items = new ArrayList<>();
    }

    // 0: print everything, 1: print completed, 2: print uncompleted
    public void printList(int completed) {
        try {
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
        } catch (Exception e) {
            System.out.println("Unexpected error printing list");
        }
    }

    public void addItem(TaskItem i) {
        try {
            items.add(i);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("This tasklist is full.");
        }
    }

    public void deleteItem(int index) {
        try {
            items.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " does not exist in this list.");
        }
    }

    public void editItem(int index, String title, String description, LocalDate dueDate) {
        try {
            items.get(index).setTitle(title);
            items.get(index).setDescription(description);
            items.get(index).setDueDate(dueDate);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + index + " does not exist in this list.");
        }
    }

    public TaskItem get(int index) {
        return items.get(index);
    }
}
