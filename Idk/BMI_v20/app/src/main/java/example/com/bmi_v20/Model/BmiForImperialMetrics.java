package example.com.bmi_v20.Model;

/**
 * Created by Rafał on 2018-03-09.
 */

public class BmiForImperialMetrics extends BMI {

    private static final double CENTIS_TO_INCHES_RATE = 0.393700787;
    private static final double KILOS_TO_POUNDS_RATE = 2.20462262;
    public final static int ENGLISH_IBM_COUNTER_CONSTANT = 703;

    public BmiForImperialMetrics(double weight, double height) {
        super(weight, height);
    }

    @Override
    public double calculateBMI() throws IllegalArgumentException {

        if (isDataValid()) {
            return (getWeight() / (getHeight() * getHeight())) * ENGLISH_IBM_COUNTER_CONSTANT;    //weight in pounds and height in inches
        } else {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    @Override
    public boolean isDataValid() {
        return (getWeight() < WEIGHT_MAX_LIMIT_KILOS * KILOS_TO_POUNDS_RATE) &&
                (getWeight() > WEIGHT_LOW_LIMIT_KILOS * KILOS_TO_POUNDS_RATE) &&
                (getHeight() < HEIGHT_MAX_LIMIT_CENTI * CENTIS_TO_INCHES_RATE) &&
                (getHeight() > HEIGHT_LOW_LIMIT_CENTI * CENTIS_TO_INCHES_RATE);
    }
    
}
