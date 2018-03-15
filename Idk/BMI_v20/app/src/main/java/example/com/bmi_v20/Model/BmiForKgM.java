package example.com.bmi_v20.Model;

/**
 * Created by Rafał on 2018-03-09.
 */

public class BmiForKgM extends BMI {

    public BmiForKgM(double weight, double height) {
        super(weight, height);
    }

    @Override
    public double calculateBMI() throws IllegalArgumentException {

        double result;

        if (isDataValid()) {
            result = getWeight() / (getHeight() / 100 * getHeight() / 100);
        } else {
            throw new IllegalArgumentException("Invalid data");
        }

        return result;
    }

    @Override
    public boolean isDataValid() {
        return (getWeight() < WEIGHT_MAX_LIMIT_KILOS) &&
                (getWeight() > WEIGHT_LOW_LIMIT_KILOS) &&
                (getHeight() < HEIGHT_MAX_LIMIT_CENTI) &&
                (getHeight() > HEIGHT_LOW_LIMIT_CENTI);
    }
}
