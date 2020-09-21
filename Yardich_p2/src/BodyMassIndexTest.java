import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BodyMassIndexTest {

    @Test
    void testBMIScore() {
        BodyMassIndex user = new BodyMassIndex(68,150);
        assertEquals(22.80493079584775, user.BMIScore());
    }

    @Test
    void testBMICategoryUnderweight() {
        BodyMassIndex user = new BodyMassIndex(100,1);
        assertEquals("Underweight <= 18.5", user.BMICategory());
    }

    @Test
    void testBMICategoryNormalWeight() {
        BodyMassIndex user = new BodyMassIndex(68,150);
        assertEquals("Normal weight = 18.5–24.9", user.BMICategory());
    }

    @Test
    void testBMICategoryOverweight() {
        BodyMassIndex user = new BodyMassIndex(68,180);
        assertEquals("Overweight = 25–29.9", user.BMICategory());
    }

    @Test
    void testBMICategoryObesity() {
        BodyMassIndex user = new BodyMassIndex(1,100);
        assertEquals("Obesity = BMI of 30 or greater", user.BMICategory());
    }
}