package zw.co.softwarezimbabwe.hivitals.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.softwarezimbabwe.hivitals.domain.Patient;
import zw.co.softwarezimbabwe.hivitals.model.PatientDTO;
import zw.co.softwarezimbabwe.hivitals.repos.PatientRepository;
import zw.co.softwarezimbabwe.hivitals.util.NotFoundException;


@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(final PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> findAll() {
        final List<Patient> patients = patientRepository.findAll(Sort.by("id"));
        return patients.stream()
                .map((patient) -> mapToDTO(patient, new PatientDTO()))
                .toList();
    }

    public PatientDTO get(final Long id) {
        return patientRepository.findById(id)
                .map(patient -> mapToDTO(patient, new PatientDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PatientDTO patientDTO) {
        final Patient patient = new Patient();
        mapToEntity(patientDTO, patient);
        return patientRepository.save(patient).getId();
    }

    public void update(final Long id, final PatientDTO patientDTO) {
        final Patient patient = patientRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(patientDTO, patient);
        patientRepository.save(patient);
    }

    public void delete(final Long id) {
        patientRepository.deleteById(id);
    }

    private PatientDTO mapToDTO(final Patient patient, final PatientDTO patientDTO) {
        patientDTO.setId(patient.getId());
        patientDTO.setPatientNumber(patient.getPatientNumber());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setSurname(patient.getSurname());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setGender(patient.getGender());
        return patientDTO;
    }

    private Patient mapToEntity(final PatientDTO patientDTO, final Patient patient) {
        patient.setPatientNumber(patientDTO.getPatientNumber());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setSurname(patientDTO.getSurname());
        patient.setAddress(patientDTO.getAddress());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setGender(patientDTO.getGender());
        return patient;
    }

    public boolean patientNumberExists(final String patientNumber) {
        return patientRepository.existsByPatientNumberIgnoreCase(patientNumber);
    }

}
