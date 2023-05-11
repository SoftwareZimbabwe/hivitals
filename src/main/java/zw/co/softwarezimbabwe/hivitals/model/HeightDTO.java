package zw.co.softwarezimbabwe.hivitals.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HeightDTO {

    private Long id;

    @Size(max = 255)
    private String patientNumber;

    private Double valueInCm;

}
