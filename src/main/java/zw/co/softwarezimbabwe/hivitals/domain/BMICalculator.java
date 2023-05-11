package zw.co.softwarezimbabwe.hivitals.domain;

public class BMICalculator {
    private double height; // in meters
    private double weight; // in kilograms
    private int age; // in years
    private boolean isChild;

    public BMICalculator(double height, double weight, int age) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.isChild = (age < 18);
    }

    public double calculateBMI() {
        double bmi = weight / Math.pow(height, 2);
        return bmi;
    }

    public String getBMICategory() {
        double bmi = calculateBMI();
        String category;

        if (isChild) {
            if (bmi < 15) {
                category = "Severe Thinness";
            } else if (bmi < 16) {
                category = "Moderate Thinness";
            } else if (bmi < 18.5) {
                category = "Mild Thinness";
            } else if (bmi < 25) {
                category = "Normal";
            } else if (bmi < 30) {
                category = "Overweight";
            } else if (bmi < 35) {
                category = "Obese Class I";
            } else if (bmi < 40) {
                category = "Obese Class II";
            } else {
                category = "Obese Class III";
            }
        } else {
            if (bmi < 16) {
                category = "Severe Thinness";
            } else if (bmi < 17) {
                category = "Moderate Thinness";
            } else if (bmi < 18.5) {
                category = "Mild Thinness";
            } else if (bmi < 25) {
                category = "Normal";
            } else if (bmi < 30) {
                category = "Overweight";
            } else if (bmi < 35) {
                category = "Obese Class I";
            } else if (bmi < 40) {
                category = "Obese Class II";
            } else {
                category = "Obese Class III";
            }
        }

        return category;
    }
}