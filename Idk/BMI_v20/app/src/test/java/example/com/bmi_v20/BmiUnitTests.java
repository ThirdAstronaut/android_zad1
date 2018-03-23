package example.com.bmi_v20;

import org.junit.Test;
import example.com.bmi_v20.Model.BMI;
import example.com.bmi_v20.Model.BmiForKgM;
import example.com.bmi_v20.Model.BmiForImperialMetrics;

import static java.sql.Types.NULL;
import static org.junit.Assert.assertEquals;

public class BmiUnitTests {

    @Test(expected = IllegalArgumentException.class)
    public void bmi_counter_throws_exception_for_missing_height_input_KgM() {
        BMI bmi = new BmiForKgM(20.0, NULL);
        bmi.calculateBMI();
    }

    @Test(expected = IllegalArgumentException.class)
    public void bmi_counter_throws_exception_for_missing_height_input_imperials() {
        BMI bmi = new BmiForImperialMetrics(20.0, NULL);
        bmi.calculateBMI();
    }

    @Test(expected = IllegalArgumentException.class)
    public void bmi_counter_throws_exception_for_missing_weight_input() {
        BMI bmi = new BmiForKgM(NULL, 20.0);
        bmi.calculateBMI();
    }

    @Test
    public void bmi_data_is_not_valid_for_missing_input() {
        BMI bmi = new BmiForKgM(20.0, NULL);
        assertEquals(bmi.isDataValid(), false);
    }

    @Test
    public void bmi_data_is_valid_for_correct_input() {
        BMI bmi = new BmiForKgM(20.0, 100.0);
        assertEquals(bmi.isDataValid(), true);
    }

    @Test
    public void bmi_calculation_is_correct_for_kgM() {
        BMI bmi = new BmiForKgM(50, 175);
        assertEquals(16.3265306, bmi.calculateBMI(), 0.01);
    }

    @Test
    public void bmi_is_correct_for_imperial_metrics() {

        BmiForImperialMetrics bmi = new BmiForImperialMetrics(110.231131, 68.8976378);
        assertEquals((bmi.getWeight() / (bmi.getHeight() * bmi.getHeight())) * bmi.ENGLISH_IBM_COUNTER_CONSTANT, bmi.calculateBMI(), 0.01);
        /**
         * @Param expected: counted manually on calculator
         */
        assertEquals(16.3265306, bmi.calculateBMI(), 0.01);

    }

}
