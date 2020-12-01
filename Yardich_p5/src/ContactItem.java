public class ContactItem {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    // Constructor
    ContactItem(String firstName, String lastName, String phoneNumber, String emailAddress) throws Exception {
        // Checks if all fields are blank
        if (!hasSomething(firstName, lastName, phoneNumber, emailAddress)) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // General editItem for changing all values at once:
    // It is not necessary to ever edit just one of the parameters in the current application, hence one "editItem" method
    public void editItem(String firstName, String lastName, String phoneNumber, String emailAddress) throws Exception {
        if (!hasSomething(firstName, lastName, phoneNumber, emailAddress)) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Checks if the given parameters contain at least one non-empty item
    public boolean hasSomething(String firstName, String lastName, String phoneNumber, String emailAddress) {
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            return false;
        }
        return true;
    }

    // Formatted String for single Contact Item
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Name: " + this.getFirstName() + " " +  this.getLastName() + "%nPhone: " + this.getPhoneNumber() + "%nEmail: " + this.getEmailAddress());
        return ret.toString();
    }
}
