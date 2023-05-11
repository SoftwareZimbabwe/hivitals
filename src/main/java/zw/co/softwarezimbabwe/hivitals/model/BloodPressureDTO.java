package zw.co.softwarezimbabwe.hivitals.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BloodPressureDTO {

    private Long id;

    @Size(max = 255)
    private String patientNumber;

    private Integer systolic;

    private Integer diastolic;

}
