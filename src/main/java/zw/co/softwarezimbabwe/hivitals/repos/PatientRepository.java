package zw.co.softwarezimbabwe.hivitals.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.softwarezimbabwe.hivitals.domain.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByPatientNumberIgnoreCase(String patientNumber);

}
