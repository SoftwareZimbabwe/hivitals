import org.junit.Test;
import static org.junit.Assert.*;

public class VitalsTest {

    @Test
    public void testCalculateBMI() {
        double height = 1.75; // in meters
        double weight = 68.5; // in kilograms
        int age = 25;

        BMICalculator calculator = new BMICalculator(height, weight, age);
        double bmi = calculator.calculateBMI();

        assertEquals(22.4, bmi, 0.1);
    }

    @Test
    public void testGetBMICategory() {
        double height = 1.75; // in meters
        double weight = 68.5; // in kilograms
        int age = 25;

        BMICalculator calculator = new BMICalculator(height, weight, age);
        String category = calculator.getBMICategory();

        assertEquals("Normal", category);
    }

    @Test
    public void testRecordBloodPressure() {
        BloodPressure bp = new BloodPressure(120, 80);
        assertEquals(120, bp.getSystolic());
        assertEquals(80, bp.getDiastolic());
    }

    @Test
    public void testRecordBloodGlucose() {
        BloodGlucose bg = new BloodGlucose(100);
        assertEquals(100, bg.getValue());
    }

    @Test
    public void testRecordWeight() {
        Weight weight = new Weight(68.5);
        assertEquals(68.5, weight.getValue(), 0.1);
    }

    @Test
    public void testRecordHeight() {
        Height height = new Height(1.75);
        assertEquals(1.75, height.getValue(), 0.1);
    }
}