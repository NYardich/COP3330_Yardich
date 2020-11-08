import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskItem {
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    TaskItem(String title, String date) {
        try {
            if(title.length() > 0) {
                LocalDate.parse(date);
                this.title = title;
            }
        } catch (DateTimeParseException e) {
            return "Invalid Date";
        } catch (exception e) {

        }
    }
}
