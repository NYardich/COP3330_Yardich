import javax.naming.NamingException;
import java.time.LocalDate;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    // Constructors

    TaskItem(String title, String description, LocalDate date) throws NamingException {
        if(title.isBlank()) {
            throw new NamingException("Title must be at least one character long.");
        }
        if (!date.isAfter(LocalDate.now())) {
            throw new NamingException("Due Date must be after today.");
        }
        this.title = title;
        this.description = description;
        this.dueDate = date;
        this.completed = false;
    }

    // This constructor will only be called in File I/O, since the user never immediately gives a value for completed;
    // Because of this, there is no check for dueDate being past today, since saved dueDates which
    // have passed since the save are allowed.
    TaskItem(String title, String description, LocalDate date, boolean completed) throws NamingException {
        if(title.isBlank()) {
            throw new NamingException("Title must be at least one character long.");
        }
        this.dueDate = date;
        this.description = description;
        this.title = title;
        this.completed = completed;
    }

    // Setters and Getters

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) throws NamingException {
        if (!dueDate.isAfter(LocalDate.now())) {
            throw new NamingException("Due Date must be after today.");
        }
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws NamingException{
        if(title.isBlank()) {
            throw new NamingException("Title must be at least one character long.");
        }
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

    // Formatted String for single Contact Item
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if(this.isCompleted()) { ret.append("*** "); }
        ret.append("[" + this.getDueDate() + "] " +  this.getTitle() + ": " + this.getDescription());
        return ret.toString();
    }
}
