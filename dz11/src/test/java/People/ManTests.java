package People;

import org.Lesson20.people.Man;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ManTests {


    private Man man;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        man = new Man("Jon", "Doe", 55, true);
        System.out.println("BeforeMethod");
    }

    @Test(groups = "GetSet", testName = "Set/Get Man beard") // тут тперевірки в одному тесті бо це boolean
    public void isBeardTest() {
        man.setBeard(true);
        Assert.assertTrue(man.isBeard(), "Test failed: man should have a beard!");
        man.setBeard(false);
        Assert.assertFalse(man.isBeard(), "Test failed: man should not have a beard!");
    }

    @Test(testName = "Man isRetired for 24, 64,65,66,96 ages")
    public void isRetiredTest() {
        man.setAge(24);
        Assert.assertFalse(man.isRetired(man.getAge()), "Test failed: man aged 24 should not be retired!");
        man.setAge(64);
        Assert.assertFalse(man.isRetired(man.getAge()), "Test failed: man aged 64 should not be retired!");
        man.setAge(65);
        Assert.assertTrue(man.isRetired(man.getAge()), "Test failed: man aged 65 should be retired!");
        man.setAge(66);
        Assert.assertTrue(man.isRetired(man.getAge()), "Test failed: man aged 66 should be retired!");
        man.setAge(96);
        Assert.assertTrue(man.isRetired(man.getAge()), "Test failed: man aged 96 should be retired!");
    }
}
