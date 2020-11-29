public class ContactItem {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    // Constructor
    ContactItem(String firstName, String lastName, String phoneNumber, String emailAddress) throws Exception {
        // Checks if all fields are blank
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Setters and Getters
    // Each setter must ensure that changing its value will not make the entire item empty

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exception {
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exception {
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws Exception {
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) throws Exception {
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.emailAddress = emailAddress;
    }


    // General editItem for changing all values at once
    public void editItem(String firstName, String lastName, String phoneNumber, String emailAddress) throws Exception {
        if (firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && emailAddress.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    // Formatted String for single Contact Item
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Name: " + this.getFirstName() + " " +  this.getLastName() + "%nPhone: " + this.getPhoneNumber() + "%nEmail: " + this.getEmailAddress());
        return ret.toString();
    }
}
