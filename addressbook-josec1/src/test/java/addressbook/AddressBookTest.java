package addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookTest {

	private final AddressBook addressBook = new AddressBook(10);
	private final Contact[] testContacts = new Contact[100];

	@BeforeEach
	public void init() {
		for (int i = 0; i < testContacts.length; i++) {
			testContacts[i] = new Contact(i, "Alice" + i, "alice@example.org", "+1 23 4567890");
		}
	}

	@Test
	void testEmptyContactBook() {
		assertEquals(10, addressBook.getCapacity());
		assertEquals(0, addressBook.getSize());
	}

	@Test
	void testAddContact() {
		assertTrue(addressBook.addContact(testContacts[0]));
		assertNotNull(addressBook.findContact(testContacts[0].getName()));
		assertEquals(1, addressBook.getSize());
	}

	@Test
	void testAddContacts() {
		for (Contact testContact : testContacts) {
			assertTrue(addressBook.addContact(testContact));
		}
		for (Contact testContact : testContacts) {
			assertNotNull(addressBook.findContact(testContact.getName()));
		}
		assertEquals(testContacts.length, addressBook.getSize());
		//System.out.println(addressBook.getCapacity());
		//System.out.println(testContacts.length);
		assertTrue(addressBook.getCapacity() >= testContacts.length);
	}

	@Test
	void testAddDuplicateContact() {
		assertTrue(addressBook.addContact(testContacts[0]));
		assertFalse(addressBook.addContact(testContacts[0]));
		assertEquals(1, addressBook.getSize());
	}

	@Test
	void testRemoveContact() {
		assertTrue(addressBook.addContact(testContacts[0]));
		assertTrue(addressBook.removeContact(testContacts[0].getName()));
		assertNull(addressBook.findContact(testContacts[0].getName()));
		assertEquals(0, addressBook.getSize());
	}

	@Test
	void testRemoveContacts() {
		// TODO run loop until testContacts.length
		// for (int i = 0; i < addressBook.getCapacity(); i++) {
		for (int i = 0; i < testContacts.length; i++) {
			assertTrue(addressBook.addContact(testContacts[i]));
		}
		//for (int i = 0; i < addressBook.getCapacity(); i++) {
		for (int i = 0; i < testContacts.length; i++) {
			assertTrue(addressBook.removeContact(testContacts[i].getName()));
		}
		//for (int i = 0; i < addressBook.getCapacity(); i++) {
			for (int i = 0; i < testContacts.length; i++) {
			assertNull(addressBook.findContact(testContacts[i].getName()));
		}
		assertEquals(0, addressBook.getSize());
	}

	@Test
	void testRemoveNonExistingContact() {
		//for (int i = 0; i < addressBook.getCapacity(); i++) {
		for (int i = 0; i < testContacts.length; i++) {
			assertTrue(addressBook.addContact(testContacts[i]));
		}
		assertFalse(addressBook.removeContact("Alice"));
	}

	@Test
	void testRemoveLast() {
		assertTrue(addressBook.addContact(
					new Contact(17, "Beat", "beat@bfh.ch", "+1 23 4567890")));
		assertTrue(addressBook.removeContact("Beat"));
	}

	@Test
	void testSetters() {

		assertThrows(NoSuchMethodException.class, () ->
								AddressBook.class.getMethod("setSize", int.class),
					"Class AddressBook shouldn't have a public method " +
								"setSize(int i)");

		assertThrows(NoSuchMethodException.class, () ->
								AddressBook.class.getMethod("setCapacity", int.class)
					, "Class AddressBook shouldn't have a public method " +
								"setCapacity(int i)");

	}

	@Test
	void testAllFieldsPrivate() {
		Field[] fields = Contact.class.getDeclaredFields();
		for (Field field : fields) {
			assertTrue(Modifier.isPrivate(field.getModifiers()),
						"The Contact class should have only private instance variables.");
		}
		fields = AddressBook.class.getDeclaredFields();
		for (Field field : fields) {
			assertTrue(Modifier.isPrivate(field.getModifiers()),
						"The AddressBook class should have only private instance variables.");
		}
	}
}
