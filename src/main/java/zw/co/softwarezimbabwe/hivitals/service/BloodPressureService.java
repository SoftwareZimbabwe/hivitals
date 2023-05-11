package zw.co.softwarezimbabwe.hivitals.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.softwarezimbabwe.hivitals.domain.BloodPressure;
import zw.co.softwarezimbabwe.hivitals.model.BloodPressureDTO;
import zw.co.softwarezimbabwe.hivitals.repos.BloodPressureRepository;
import zw.co.softwarezimbabwe.hivitals.util.NotFoundException;


@Service
public class BloodPressureService {

    private final BloodPressureRepository bloodPressureRepository;

    public BloodPressureService(final BloodPressureRepository bloodPressureRepository) {
        this.bloodPressureRepository = bloodPressureRepository;
    }

    public List<BloodPressureDTO> findAll() {
        final List<BloodPressure> bloodPressures = bloodPressureRepository.findAll(Sort.by("id"));
        return bloodPressures.stream()
                .map((bloodPressure) -> mapToDTO(bloodPressure, new BloodPressureDTO()))
                .toList();
    }

    public BloodPressureDTO get(final Long id) {
        return bloodPressureRepository.findById(id)
                .map(bloodPressure -> mapToDTO(bloodPressure, new BloodPressureDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BloodPressureDTO bloodPressureDTO) {
        final BloodPressure bloodPressure = new BloodPressure();
        mapToEntity(bloodPressureDTO, bloodPressure);
        return bloodPressureRepository.save(bloodPressure).getId();
    }

    public void update(final Long id, final BloodPressureDTO bloodPressureDTO) {
        final BloodPressure bloodPressure = bloodPressureRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bloodPressureDTO, bloodPressure);
        bloodPressureRepository.save(bloodPressure);
    }

    public void delete(final Long id) {
        bloodPressureRepository.deleteById(id);
    }

    private BloodPressureDTO mapToDTO(final BloodPressure bloodPressure,
            final BloodPressureDTO bloodPressureDTO) {
        bloodPressureDTO.setId(bloodPressure.getId());
        bloodPressureDTO.setPatientNumber(bloodPressure.getPatientNumber());
        bloodPressureDTO.setSystolic(bloodPressure.getSystolic());
        bloodPressureDTO.setDiastolic(bloodPressure.getDiastolic());
        return bloodPressureDTO;
    }

    private BloodPressure mapToEntity(final BloodPressureDTO bloodPressureDTO,
            final BloodPressure bloodPressure) {
        bloodPressure.setPatientNumber(bloodPressureDTO.getPatientNumber());
        bloodPressure.setSystolic(bloodPressureDTO.getSystolic());
        bloodPressure.setDiastolic(bloodPressureDTO.getDiastolic());
        return bloodPressure;
    }

}
