package zw.co.softwarezimbabwe.hivitals.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PatientDTO {

    private Long id;

    @Size(max = 255)
    private String patientNumber;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String surname;

    @Size(max = 255)
    private String address;

    private LocalDate dateOfBirth;

    @Size(max = 255)
    private String gender;

}
