/*
 * Project and Training 1 - Computer Science, Berner Fachhochschule
 */

package addressbook;

import java.util.Scanner;

public class AddressBook {

    private Contact[] contactList;
    private int maxSizeContact;
    private int currentSize;

    public AddressBook(int maxSizeContact) {
        this.maxSizeContact = maxSizeContact;
        this.contactList = new Contact[maxSizeContact];
        this.currentSize = 0;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter max. number of contacts: ");
        int maxSizeContact = in.nextInt();
        in.nextLine();
        AddressBook addressBook = new AddressBook(maxSizeContact);

        while (true) {
            System.out.println("Enter number for choosing your options");
            System.out.println("1. Add contact");
            System.out.println("2. Remove contact");
            System.out.println("3. Find contact");
            System.out.println("4. View contact list size");
            System.out.println("5. Exit");
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter ID-number: ");
                    int id = in.nextInt();
                    in.nextLine();

                    System.out.println("Enter name: ");
                    String name = in.nextLine();


                    System.out.println("Enter email: ");
                    String email = in.nextLine();

                    System.out.println("Enter phone number: ");
                    String phone = in.nextLine();

                    Contact newContact = new Contact(id, name, email, phone);
                    boolean added = addressBook.addContact(newContact);

                    if (added) {
                        System.out.println("Contact added");
                    } else {
                        System.out.println("Failed to add contact. Contact already exists or contact saving capacity reached");
                    }
                    break;

                case 2:
                    System.out.println("Enter name to remove: ");
                    String removeContactDetails = in.nextLine();
                    boolean removed = addressBook.removeContact(removeContactDetails);
                    if (removed) {
                        System.out.println("Contact" + removeContactDetails + "removed");
                    } else {
                        System.out.println("Contact not found");
                    }
                    break;

                case 3:
                    System.out.println("Enter name to find: ");
                    String findContactDetails = in.nextLine();
                    Contact savedContact = addressBook.findContact(findContactDetails);
                    if (savedContact != null) {
                        System.out.println("Contact" + findContactDetails + "found");
                    } else {
                        System.out.println("Contact not found");
                    }
                    break;

                case 4:
                    System.out.println("AddressBook Capacity: " + addressBook.getCapacity());
                    System.out.println("Number of Contacts: " + addressBook.getSize());
                    break;

                case 5:
                    System.out.println("Exit program.");
                    in.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");

            }
        }
    }



    public int getCapacity() {
        return maxSizeContact;
    }

    public int getSize() {
        return currentSize;

    }

    public boolean addContact (Contact newContact) {
        if (currentSize>=maxSizeContact) {
            System.out.println("Contact size exceeded. Increase capacity limit. ");
            increaseCapacity();
        }

        for (Contact contact: contactList) {
            if (contact != null && contact.getName().equals(newContact.getName())){
                System.out.println("Contact already exists");
                return false;
            }
        }
            contactList[currentSize] = newContact;
            currentSize++;
            return true;

    }

    public Contact findContact (String name){

        for (Contact contact : contactList) {
            if (contact != null && contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public boolean removeContact (String name){

        for (int i = 0; i < currentSize; i++) {
            if (contactList[i] != null && contactList[i].getName().equalsIgnoreCase(name)) {
                for (int j=i; j<currentSize-1; j++){
                    contactList[j] = contactList[j+1];
                }
                contactList[currentSize-1] = null;
                currentSize--;
                return true;
            }
        }
        return false;
    }

    private void increaseCapacity() {
        System.out.println("Increasing capacity from " + maxSizeContact + " to " + (maxSizeContact * 2));
        maxSizeContact *= 2;
        Contact [] newContactList = new Contact[maxSizeContact ];

        for (int i = 0; i < currentSize; i++) {
            newContactList[i] = contactList[i];
        }

        contactList = newContactList;

    }

}





