package People;

import org.Lesson20.people.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonFuncTest {
    private Person person;
    private Person partner1;
    private Person partner2;
    private final String name = "Name";
    private final String lastName = "LastName";
    private final int age = 55;

    @BeforeMethod
    public void setUp() {
        person = new Person(name, lastName, age);
        partner1 = new Person("P1name", "P1lastName", 44);
        partner2 = new Person("P2name", "P2lastName", 47);
    }

    @Test(testName = "Person isRetired for 24,59,60,61,96 ages")
    public void isRetiredTest() {
        person.setAge(24);
        Assert.assertFalse(person.isRetired(person.getAge()), "Test failed: person aged 24 should not be retired!");
        person.setAge(59);
        Assert.assertFalse(person.isRetired(person.getAge()), "Test failed: person aged 59 should not be retired!");
        person.setAge(60);
        Assert.assertTrue(person.isRetired(person.getAge()), "Test failed: person aged 60 should be retired!");
        person.setAge(61);
        Assert.assertTrue(person.isRetired(person.getAge()), "Test failed: person aged 61 should be retired!");
        person.setAge(96);
        Assert.assertTrue(person.isRetired(person.getAge()), "Test failed: person aged 96 should be retired!");
    }

    @Test(testName = "Change Last Name Test")
    public void changeLastNameTest() {
        String newLastName = "newLastName";
        person.changeLastName(person, newLastName);
        Assert.assertEquals(person.getLastName(), newLastName, "Test failed: last name was not updated!");
        Assert.assertEquals(person.getOldLastName(), lastName, "Test failed: old last name was not saved!");
        Assert.assertTrue(person.isChangedLastName(), "Test failed: isChangedLastName flag was not set to true!");
    }

    // getFirstName() в тести додано для читабельності помилок
    @Test(testName = "Set Partner Successfully")
    public void setPartnerSuccessfullyTest() {
        person.setPartner(partner1);
        Assert.assertEquals(person.getPartner().getFirstName(), partner1.getFirstName(), "Test failed: person's partner should be person2!");
        Assert.assertEquals(partner1.getPartner().getFirstName(), person.getFirstName(), "Test failed: person2's partner should be person1!");
    }

    @Test(testName = "Set Partner When One Already Has Partner")
    public void setPartnerWhenOneAlreadyHasPartnerTest() {
        person.setPartner(partner1);
        partner2.setPartner(partner1);

        Assert.assertEquals(person.getPartner().getFirstName(), partner1.getFirstName(), "Test failed: person's partner should still be partner1!");
        Assert.assertEquals(partner1.getPartner().getFirstName(), person.getFirstName(), "Test failed: partner1's partner should still be person!");
        Assert.assertNull(partner2.getPartner(), "Test failed: partner2 should not have a partner!");
    }

    @Test(testName = "Set Partner When Both Already Have Partners")
    public void setPartnerWhenBothAlreadyHavePartnersTest() {
        person.setPartner(partner1);
        partner2.setPartner(new Person("Bob", "Brown", 40));
        person.setPartner(partner2);

        Assert.assertEquals(person.getPartner().getFirstName(), partner1.getFirstName(), "Test failed: person's partner should still be partner1!");
        Assert.assertEquals(partner1.getPartner().getFirstName(), person.getFirstName(), "Test failed: partner1's partner should still be person!");
        Assert.assertNotEquals(partner2.getPartner().getFirstName(), person.getFirstName(), "Test failed: partner2's partner should not be person!");
    }


}
