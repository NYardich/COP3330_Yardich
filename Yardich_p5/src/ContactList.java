import javax.naming.NamingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
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

    public void editItem(int index, String firstName, String lastName, String phoneNumber, String emailAddress) throws Exception {
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact");
        }
        items.get(index).setFirstName(firstName);
        items.get(index).setLastName(lastName);
        items.get(index).setPhoneNumber(phoneNumber);
        items.get(index).setEmailAddress(emailAddress);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void write(String filename) {
        try(Formatter output = new Formatter(filename + ".txt")) {
            for(int i = 0; i < this.size(); i++) {
                output.format("%s%n%s%n%s%n%s%n", items.get(i).getFirstName(), items.get(i).getLastName(), items.get(i).getPhoneNumber(), items.get(i).getEmailAddress());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println("Unknown error in File Write: " + e.getMessage());
        }
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

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            ret.append(i + ")%n" + items.get(i) + "%n");
        }
        return ret.toString();
    }
}
