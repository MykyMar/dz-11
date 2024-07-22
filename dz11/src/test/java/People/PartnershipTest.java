package People;

import org.Lesson20.people.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PartnershipTest {
    private Person person1;
    private Person person2;
    private final String lastName1 = "Doe";
    private final String lastName2 = "Smith";

    @BeforeMethod
    public void setUp() {
        person1 = new Person("John", lastName1, 30);
        person2 = new Person("Jane", lastName2, 28);
    }

    // коментарі додав ChatGPT))
    // getFirstName() в тести додано для читабельності помилок
    @Test(testName = "Register Partnership With LastName Change")
    public void registerPartnershipWithLastNameChangeTest() {
        person1.registerPartnership(person2, true, false);

        // Check that the partnership is registered
        Assert.assertEquals(person1.getPartner().getFirstName(), person2.getFirstName(), "Test failed: person1's partner should be person2!");
        Assert.assertEquals(person2.getPartner().getFirstName(), person1.getFirstName(), "Test failed: person2's partner should be person1!");

        // Check that the last name is changed correctly
        Assert.assertEquals(person1.getLastName(), lastName2, "Test failed: person1's last name should be changed to person2's last name!");
        Assert.assertEquals(person2.getLastName(), lastName2, "Test failed: person2's last name should remain unchanged!");

        // Check that the old last name is saved correctly
        Assert.assertEquals(person1.getOldLastName(), lastName1, "Test failed: person1's old last name should be saved!");
    }

    @Test(testName = "Register Partnership With Both LastName Change Attempt")
    public void registerPartnershipWithBothLastNameChangeAttemptTest() {
        person1.registerPartnership(person2, true, true);

        // Check that the partnership is not registered
        Assert.assertNull(person1.getPartner(), "Test failed: person1's partner should be null!");
        Assert.assertNull(person2.getPartner(), "Test failed: person2's partner should be null!");

        // Check that the last names remain unchanged
        Assert.assertEquals(person1.getLastName(), lastName1, "Test failed: person1's last name should remain unchanged!");
        Assert.assertEquals(person2.getLastName(), lastName2, "Test failed: person2's last name should remain unchanged!");
    }

    @Test(testName = "Deregister Partnership With LastName Reversion")
    public void deregisterPartnershipWithLastNameReversionTest() {
        person1.registerPartnership(person2, true, false);
        person1.deregisterPartnership(true, false);

        // Check that the partnership is deregistered
        Assert.assertNull(person1.getPartner(), "Test failed: person1's partner should be null after deregistration!");
        Assert.assertNull(person2.getPartner(), "Test failed: person2's partner should be null after deregistration!");

        // Check that the last names are reverted correctly
        Assert.assertEquals(person1.getLastName(), lastName1, "Test failed: person1's last name should be reverted to the old last name!");
        Assert.assertEquals(person2.getLastName(), lastName2, "Test failed: person2's last name should remain unchanged!");
    }

    @Test(testName = "Deregister Partnership Without LastName Reversion")
    public void deregisterPartnershipWithoutLastNameReversionTest() {
        person1.registerPartnership(person2, false, true);
        person1.deregisterPartnership(false, true);

        // Check that the partnership is deregistered
        Assert.assertNull(person1.getPartner(), "Test failed: person1's partner should be null after deregistration!");
        Assert.assertNull(person2.getPartner(), "Test failed: person2's partner should be null after deregistration!");

        // Check that the last names remain as they were changed
        Assert.assertEquals(person1.getLastName(), lastName1, "Test failed: person1's last name should remain unchanged!");
        Assert.assertEquals(person2.getLastName(), lastName2, "Test failed: person2's last name should be reverted to the old last name!");
    }
}
