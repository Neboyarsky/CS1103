package Week1.RA_W1.Searches;

public class ContactsDemo {
    public static void main(String[] args) {
        Contact[] contacts = new Contact[5];
        contacts[0] = new Contact("Eric", "Cartman");
        contacts[1] = new Contact("Kyle", "Broflovsky");
        contacts[2] = new Contact("Stan", "Marsh");
        contacts[3] = new Contact("Kenny", "McCormick");
        contacts[4] = new Contact();

        for (int i = 0; i < contacts.length; i++) {
            System.out.println(contacts[i].getFirstName() + " " + contacts[i].getLastName());
        }

    }
}
