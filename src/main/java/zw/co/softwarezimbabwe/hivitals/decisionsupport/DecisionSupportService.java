package zw.co.softwarezimbabwe.hivitals.decisionsupport;

import org.springframework.stereotype.Service;

@Service
public class DecisionSupportService {
    
    String bgCategory;
    String bpCategory;
    String weightCategory;
    String heightCategory;

    public DecisionSupport provideDecisionSupport(double bloodGlucose, double systolicBP, double diastolicBP, double weight, double height) {
        DecisionSupport decisionSupport = new DecisionSupport(bloodGlucose, systolicBP, diastolicBP, weight, height);

        this.bgCategory = decisionSupport.getBloodGlucoseCategory();
        this.bpCategory = decisionSupport.getBPCategory();
        this.weightCategory = decisionSupport.getWeightCategory();
        this.heightCategory = decisionSupport.getHeightCategory();

        return decisionSupport;
    }

    public String provideDecisionSupport(double bloodGlucose, double systolicBP, double diastolicBP, double weight, double height, int age, String gender) {
        String decision = "";

        // Calculate BMI
        double bmi = weight / Math.pow(height, 2);

        // Determine BMI category
        String bmiCategory = "";
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmi < 25) {
            bmiCategory = "Normal";
        } else if (bmi < 30) {
            bmiCategory = "Overweight";
        } else {
            bmiCategory = "Obese";
        }

        // Check if patient is a child or an adult
        boolean isChild = age < 18;

        // Check if patient is male or female
        boolean isMale = gender.equalsIgnoreCase("male");

        // Determine blood glucose category
        String bgCategory = "";
        if (bloodGlucose < 70) {
            bgCategory = "Low";
        } else if (bloodGlucose < 100) {
            bgCategory = "Normal";
        } else if (bloodGlucose < 126) {
            bgCategory = "High";
        } else {
            bgCategory = "Very High";
        }

        // Determine blood pressure category
        String bpCategory = "";
        if (systolicBP < 90 && diastolicBP < 60) {
            bpCategory = "Low";
        } else if (systolicBP < 120 && diastolicBP < 80) {
            bpCategory = "Normal";
        } else if (systolicBP < 130 || diastolicBP < 90) {
            bpCategory = "High";
        } else {
            bpCategory = "Very High";
        }

        // Determine height category
        String heightCategory = "";
        if (height < 1.5) {
            heightCategory = "Short";
        } else if (height < 1.8) {
            heightCategory = "Normal";
        } else {
            heightCategory = "Tall";
        }

        // Determine if patient needs diabetes screening
        boolean needsDiabetesScreening = false;
        if ((bgCategory.equals("High") || bgCategory.equals("Very High")) && !isChild) {
            needsDiabetesScreening = true;
        }

        // Determine if patient needs blood pressure medication
        boolean needsBloodPressureMedication = false;
        if ((bpCategory.equals("High") || bpCategory.equals("Very High")) && !isChild) {
            needsBloodPressureMedication = true;
        }

        // Determine if patient needs weight loss counseling
        boolean needsWeightLossCounseling = false;
        if (bmiCategory.equals("Obese") && !isChild) {
            needsWeightLossCounseling = true;
        }

        // Determine if patient needs growth hormone therapy
        boolean needsGrowthHormoneTherapy = false;
        if (isChild && isMale && heightCategory.equals("Short")) {
            needsGrowthHormoneTherapy = true;
        }

        // Create decision string based on the categories and needs
        if (needsDiabetesScreening) {
            decision += "Patient needs diabetes screening. ";
        }
        if (needsBloodPressureMedication) {
            decision += "Patient needs blood pressure medication. ";
        }
        if (needsWeightLossCounseling) {
            decision += "Patient needs weight loss counseling. ";
        }
        if (needsGrowthHormoneTherapy) {
            decision += "Patient needs growth hormone therapy. ";
        }
        if (decision.equals("")) {
            decision = "Patient is healthy and does not need any interventions. ";
        }

        // Return decision string
        return decision;
    }
}