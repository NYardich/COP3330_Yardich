import java.util.InputMismatchException;

public class ContactApp extends ListApplication<ContactList, ContactItem> {

    // Starting, public menu to access other methods.
    @Override
    public void mainMenu() {
        boolean repeat = true;
        while (repeat) {
            System.out.printf("Main Menu%n---------%n");
            try {
                System.out.printf("1) create a new list%n2) load an existing list%n3) quit%n\t> ");
                int response = input.nextInt();
                input.nextLine();
                repeat = mainMenuDecision(response);
            } catch (InputMismatchException e) {
                System.out.println("Answer must be an integer");
                input.nextLine();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Menu to access and modify a ContactList
    @Override
    protected void listOperationMenu(ContactList current) {
        boolean repeat = true;
        while (repeat) {
            System.out.printf("%nList Operation Menu%n---------%n");
            try {
                System.out.printf("1) view the list%n2) add an item%n3) edit an item%n4) remove an item%n5) save the current list%n6) quit to the main menu%n\t> ");
                int response = input.nextInt();
                input.nextLine();
                repeat = listOperationMenuDecision(response, current);
            } catch (InputMismatchException e) {
                System.out.println("Answer must be an integer");
                input.nextLine();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Deals with response to Main Menu Prompt
    @Override
    protected boolean mainMenuDecision(int response) {
        switch (response) {
            case 1:
                ContactList here = new ContactList();
                System.out.println("new contact list has been created");
                listOperationMenu(here);
                return true;
            case 2:
                try {
                    ContactList file = readFile();
                    listOperationMenu(file);
                }
                catch (Exception e) {
                    System.out.println("File Read Failed.");
                } finally {
                    return true;
                }
            case 3:
                System.out.println("Exiting Contact List Creation...");
                return false;
            default:
                System.out.println("Answer must be 1, 2, or 3.");
                return true;
        }
    }

    // Deals with response to List Operation Menu Prompt
    @Override
    protected boolean listOperationMenuDecision(int response, ContactList current) {
        switch (response) {
            case 1:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                return true;
            case 2:
                current.addItem(addPrompt());
                return true;
            case 3:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                editPrompt(current);
                return true;
            case 4:
                System.out.printf("%nCurrent Tasks%n-------------%n" + current.toString());
                removePrompt(current);
                return true;
            case 5:
                writeToFile(current);
                return true;
            case 6:
                return false;
            default:
                System.out.println("Answer must be between 1 and 6.");
                return true;
        }
    }

    // Prompts user to enter fields for new contact item, then attempts to create that item
    @Override
    protected ContactItem addPrompt() {
        while (true) {
            try {
                System.out.print("First name: ");
                String firstName = input.nextLine();
                System.out.print("Last name: ");
                String lastName = input.nextLine();
                System.out.print("Phone number (xxx-xxx-xxxx): ");
                String phoneNumber = input.nextLine();
                System.out.print("Email address (x@y.z): ");
                String emailAddress = input.nextLine();

                return new ContactItem(firstName, lastName, phoneNumber, emailAddress);
            } catch (InputMismatchException e) {
                System.out.println("Incorrect Input type: Contact was not created.");
                input.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage() + ": Contact was not created.");
            }
        }
    }

    // Prompts user on which contact to remove, then attempts to remove that contact
    @Override
    protected void removePrompt(ContactList current) {
        try {
            System.out.print("Enter the index of the contact you wish to delete: ");
            int index = input.nextInt();

            current.deleteItem(index);
        } catch(InputMismatchException e) {
            System.out.println("Index must be an integer.");
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Index does not exist.");
        } catch(Exception e) {
            System.out.println(e.getMessage() + ": Task was not deleted.");
        } finally {
            input.nextLine();
        }
    }

    // Prompts user on which contact to edit, asks for fields for that contact, then attempts to edit it
    @Override
    protected void editPrompt(ContactList current) {
        try {
            System.out.print("Enter the index of the task you wish to edit: ");
            int index = input.nextInt();
            input.nextLine();
            System.out.printf("Enter a new first name for contact %d: ", index);
            String firstName = input.nextLine();
            System.out.printf("Enter a new last name for contact %d: ", index);
            String lastName = input.nextLine();
            System.out.printf("Enter a new phone number (xxx-xxx-xxxx) for contact %d: ", index);
            String phoneNumber = input.nextLine();
            System.out.printf("Enter a new email address (x@y.z) for contact %d: ", index);
            String emailAddress = input.nextLine();

            current.editItem(index, firstName, lastName, phoneNumber, emailAddress);
        } catch (InputMismatchException e) {
            System.out.println("Incorrect Input type: Task was not edited.");
            input.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index does not exist in this list: Task was not edited.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": Task was not edited.");
        }
    }

    // Prompts user on filename to write to, then attempts to write a ContactList to that file
    @Override
    protected void writeToFile(ContactList current) {
        System.out.printf("What is your desired filename? (No need for file extension)%n\t> ");
        String filename = input.nextLine();
        current.write(filename);
    }

    // Prompts user on filename to read from, then attempts to create a ContactList from that file
    @Override
    protected ContactList readFile() throws Exception {
        System.out.printf("What is the name of your file? Make sure it is .txt and within this directory (No need for file extension)%n\t> ");
        String filename = input.nextLine();
        ContactList ret = new ContactList();

        // read returns a boolean indicating if the file read was successful or not
        if(ret.read(filename)) {
            return ret;
        } else {
            // This exception indicates to the caller that it will not be receiving a Contact List from the file
            throw new Exception("File Read Failed.");
        }
    }
}
