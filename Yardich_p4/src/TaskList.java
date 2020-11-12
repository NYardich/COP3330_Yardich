import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
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

    public void addItem(TaskItem i) {
        items.add(i);
    }

    public void deleteItem(int index) {
        items.remove(index);
    }

    public void editItem(int index, String title, String description, LocalDate dueDate) throws Exception {
        items.get(index).setTitle(title);
        items.get(index).setDescription(description);
        items.get(index).setDueDate(dueDate);
    }

    public TaskItem get(int index) {
        return items.get(index);
    }

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
}
