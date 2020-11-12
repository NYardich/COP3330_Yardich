import java.time.LocalDate;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    TaskItem(String title, String description, LocalDate date) throws Exception{
        if (date.isAfter(LocalDate.now())) {
            this.dueDate = date;
        } else {
            throw new Exception("Due Date must be after today");
        }
        this.description = description;
        if(title.length() > 0) {
            this.title = title;
        } else  {
            throw new Exception("Title must be at least one character long");
        }
        this.completed = false;
    }

    TaskItem(String title, String description, LocalDate date, boolean completed) {
        this.dueDate = date;
        this.description = description;
        this.title = title;
        this.completed = completed;
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

    public void setTitle(String title) throws Exception{
        if(title.length() > 0) {
            this.title = title;
        } else  {
            throw new Exception("Title must be at least one character long");
        }
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
