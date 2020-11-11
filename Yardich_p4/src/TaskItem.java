import java.time.LocalDate;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    TaskItem(String title, String description, LocalDate date) {
        this.dueDate = date;
        this.description = description;
        this.title = title;
        this.completed = false;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if(completed) { ret.append("*** "); }
        ret.append("[" + this.getDueDate() + "] " +  this.getTitle() + ": " + this.getDescription());
        return ret.toString();
    }
}
