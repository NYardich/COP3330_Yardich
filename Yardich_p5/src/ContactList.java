import javax.naming.NamingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ContactList implements ItemList<ContactItem>{
    private ArrayList<ContactItem> items;

    // Constructor
    ContactList() { this.items = new ArrayList<>(); }

    // Add Contact Item in Contact List
    @Override
    public void addItem(ContactItem item) {
        items.add(item);
    }

    // Delete Contact Item in Contact List at given index
    @Override
    public void deleteItem(int index) {
        items.remove(index);
    }

    // Edit Contact Item at given index with given values
    public void editItem(int index, String firstName, String lastName, String phoneNumber, String emailAddress) throws Exception {
        // Checks if every value given is blank
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact");
        }
        items.get(index).editItem(firstName, lastName, phoneNumber, emailAddress);
    }

    // Return size of Contact List
    @Override
    public int size() {
        return items.size();
    }

    // Write Contact List to File
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

    // Read Contact List from File
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
        } catch (FileNotFoundException e) {
            System.out.println("The file name was not found in this directory.");
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred in file I/O; It's likely that the format in which the data was saved is not supported.");
            return false;
        }
    }

    // Formatted string for list of Contact Items
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            ret.append(i + ")%n" + items.get(i) + "%n");
        }
        return ret.toString();
    }

    // Simple getters with indexes, allowing Users to find values at indexes without being able to edit them

    public String getFirstName(int index) {
        return items.get(index).getFirstName();
    }

    public String getLastName(int index) {
        return items.get(index).getLastName();
    }

    public String getPhoneNumber(int index) {
        return items.get(index).getPhoneNumber();
    }

    public String getEmailAddress(int index) {
        return items.get(index).getEmailAddress();
    }

}
