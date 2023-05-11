package zw.co.softwarezimbabwe.hivitals.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class WeightDTO {

    private Long id;

    @Size(max = 255)
    private String patientNumber;

    private Double valueInKg;

}
