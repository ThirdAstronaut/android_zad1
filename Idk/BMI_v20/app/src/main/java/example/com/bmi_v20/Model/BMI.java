package example.com.bmi_v20.Model;

/**
 * Created by Rafał on 2018-03-08.
 */

public abstract class BMI {

    static final int WEIGHT_LOW_LIMIT_KILOS = 10;
    static final int WEIGHT_MAX_LIMIT_KILOS = 300;
    static final int HEIGHT_LOW_LIMIT_CENTI = 30;
    static final int HEIGHT_MAX_LIMIT_CENTI = 300;

    private double weight;
    private double height;

    BMI(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    public abstract double calculateBMI();

    public abstract boolean isDataValid();

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

}
