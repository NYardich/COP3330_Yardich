public class ContactItem {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    ContactItem(String FN, String LN, String PN, String EA) throws Exception {
        if (FN.isBlank() && LN.isBlank() && PN.isBlank() && EA.isBlank()) {
            throw new Exception("There must be at least one parameter in the Contact");
        }
        this.firstName = FN;
        this.lastName = LN;
        this.phoneNumber = PN;
        this.emailAddress = EA;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean hasItems() {
        if(this.getFirstName().isBlank() && this.getLastName().isBlank() && this.getEmailAddress().isBlank() && this.getPhoneNumber().isBlank()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("Name: " + this.getFirstName() + " " +  this.getLastName() + "%nPhone: " + this.getPhoneNumber() + "%nEmail: " + this.getEmailAddress());
        return ret.toString();
    }
}
