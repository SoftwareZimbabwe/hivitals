package zw.co.softwarezimbabwe.hivitals.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.softwarezimbabwe.hivitals.domain.BloodGlucose;
import zw.co.softwarezimbabwe.hivitals.model.BloodGlucoseDTO;
import zw.co.softwarezimbabwe.hivitals.repos.BloodGlucoseRepository;
import zw.co.softwarezimbabwe.hivitals.util.NotFoundException;


@Service
public class BloodGlucoseService {

    private final BloodGlucoseRepository bloodGlucoseRepository;

    public BloodGlucoseService(final BloodGlucoseRepository bloodGlucoseRepository) {
        this.bloodGlucoseRepository = bloodGlucoseRepository;
    }

    public List<BloodGlucoseDTO> findAll() {
        final List<BloodGlucose> bloodGlucoses = bloodGlucoseRepository.findAll(Sort.by("id"));
        return bloodGlucoses.stream()
                .map((bloodGlucose) -> mapToDTO(bloodGlucose, new BloodGlucoseDTO()))
                .toList();
    }

    public BloodGlucoseDTO get(final Long id) {
        return bloodGlucoseRepository.findById(id)
                .map(bloodGlucose -> mapToDTO(bloodGlucose, new BloodGlucoseDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BloodGlucoseDTO bloodGlucoseDTO) {
        final BloodGlucose bloodGlucose = new BloodGlucose();
        mapToEntity(bloodGlucoseDTO, bloodGlucose);
        return bloodGlucoseRepository.save(bloodGlucose).getId();
    }

    public void update(final Long id, final BloodGlucoseDTO bloodGlucoseDTO) {
        final BloodGlucose bloodGlucose = bloodGlucoseRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(bloodGlucoseDTO, bloodGlucose);
        bloodGlucoseRepository.save(bloodGlucose);
    }

    public void delete(final Long id) {
        bloodGlucoseRepository.deleteById(id);
    }

    private BloodGlucoseDTO mapToDTO(final BloodGlucose bloodGlucose,
            final BloodGlucoseDTO bloodGlucoseDTO) {
        bloodGlucoseDTO.setId(bloodGlucose.getId());
        bloodGlucoseDTO.setPatientNumber(bloodGlucose.getPatientNumber());
        bloodGlucoseDTO.setMolarConcentration(bloodGlucose.getMolarConcentration());
        return bloodGlucoseDTO;
    }

    private BloodGlucose mapToEntity(final BloodGlucoseDTO bloodGlucoseDTO,
            final BloodGlucose bloodGlucose) {
        bloodGlucose.setPatientNumber(bloodGlucoseDTO.getPatientNumber());
        bloodGlucose.setMolarConcentration(bloodGlucoseDTO.getMolarConcentration());
        return bloodGlucose;
    }

}
