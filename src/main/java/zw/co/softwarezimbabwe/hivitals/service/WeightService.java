package zw.co.softwarezimbabwe.hivitals.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.softwarezimbabwe.hivitals.domain.Weight;
import zw.co.softwarezimbabwe.hivitals.model.WeightDTO;
import zw.co.softwarezimbabwe.hivitals.repos.WeightRepository;
import zw.co.softwarezimbabwe.hivitals.util.NotFoundException;


@Service
public class WeightService {

    private final WeightRepository weightRepository;

    public WeightService(final WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    public List<WeightDTO> findAll() {
        final List<Weight> weights = weightRepository.findAll(Sort.by("id"));
        return weights.stream()
                .map((weight) -> mapToDTO(weight, new WeightDTO()))
                .toList();
    }

    public WeightDTO get(final Long id) {
        return weightRepository.findById(id)
                .map(weight -> mapToDTO(weight, new WeightDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final WeightDTO weightDTO) {
        final Weight weight = new Weight();
        mapToEntity(weightDTO, weight);
        return weightRepository.save(weight).getId();
    }

    public void update(final Long id, final WeightDTO weightDTO) {
        final Weight weight = weightRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(weightDTO, weight);
        weightRepository.save(weight);
    }

    public void delete(final Long id) {
        weightRepository.deleteById(id);
    }

    private WeightDTO mapToDTO(final Weight weight, final WeightDTO weightDTO) {
        weightDTO.setId(weight.getId());
        weightDTO.setPatientNumber(weight.getPatientNumber());
        weightDTO.setValueInKg(weight.getValueInKg());
        return weightDTO;
    }

    private Weight mapToEntity(final WeightDTO weightDTO, final Weight weight) {
        weight.setPatientNumber(weightDTO.getPatientNumber());
        weight.setValueInKg(weightDTO.getValueInKg());
        return weight;
    }

}
