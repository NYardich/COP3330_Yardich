import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList implements ItemList<ContactItem>{
    private ArrayList<ContactItem> items;

    ContactList() { this.items = new ArrayList<>(); }

    @Override
    public void addItem(ContactItem item) {
        items.add(item);
    }

    @Override
    public void deleteItem(int index) {
        items.remove(index);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void write(String filename) {

    }

    @Override
    public boolean read(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename + ".txt"));
            while (sc.hasNext()) {
                String firstName = sc.nextLine();
                String lastName = sc.nextLine();
                String phoneNumber = sc.nextLine();
                String emailAddress = sc.nextLine();
                this.addItem(new ContactItem(firstName, lastName, phoneNumber, emailAddress));
            }
            System.out.printf("File Received%n");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
