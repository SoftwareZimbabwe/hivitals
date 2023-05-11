package zw.co.softwarezimbabwe.hivitals.decisionsupport;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class DecisionSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double bloodGlucose;
    private double systolicBP;
    private double diastolicBP;
    private double weight;
    private double height;

    public DecisionSupport(double bloodGlucose, double systolicBP, double diastolicBP, double weight, double height) {
        this.bloodGlucose = bloodGlucose;
        this.systolicBP = systolicBP;
        this.diastolicBP = diastolicBP;
        this.weight = weight;
        this.height = height;
    }

    public DecisionSupport() {}

    // getters and setters
    // ...

    public String getBloodGlucoseCategory() {
        if (bloodGlucose < 70) {
            return "Low";
        } else if (bloodGlucose < 100) {
            return "Normal";
        } else if (bloodGlucose < 126) {
            return "High";
        } else {
            return "Very High";
        }
    }

    public String getBPCategory() {
        if (systolicBP < 90 && diastolicBP < 60) {
            return "Low";
        } else if (systolicBP < 120 && diastolicBP < 80) {
            return "Normal";
        } else if (systolicBP < 130 || diastolicBP < 90) {
            return "High";
        } else {
            return "Very High";
        }
    }

    public String getWeightCategory() {
        double bmi = weight / Math.pow(height, 2);
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public String getHeightCategory() {
        if (height < 1.5) {
            return "Short";
        } else if (height < 1.8) {
            return "Normal";
        } else {
            return "Tall";
        }
    }
}