package Week1.RA_W1.Searches;

public class Contact {
    private final String firstName;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact() {
        this.firstName = "defaultFirstName";
        this.lastName = "defaultLastName";
    }

    private final String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
