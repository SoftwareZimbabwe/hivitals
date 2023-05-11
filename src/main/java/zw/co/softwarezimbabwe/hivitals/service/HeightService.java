package zw.co.softwarezimbabwe.hivitals.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.softwarezimbabwe.hivitals.domain.Height;
import zw.co.softwarezimbabwe.hivitals.model.HeightDTO;
import zw.co.softwarezimbabwe.hivitals.repos.HeightRepository;
import zw.co.softwarezimbabwe.hivitals.util.NotFoundException;


@Service
public class HeightService {

    private final HeightRepository heightRepository;

    public HeightService(final HeightRepository heightRepository) {
        this.heightRepository = heightRepository;
    }

    public List<HeightDTO> findAll() {
        final List<Height> heights = heightRepository.findAll(Sort.by("id"));
        return heights.stream()
                .map((height) -> mapToDTO(height, new HeightDTO()))
                .toList();
    }

    public HeightDTO get(final Long id) {
        return heightRepository.findById(id)
                .map(height -> mapToDTO(height, new HeightDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final HeightDTO heightDTO) {
        final Height height = new Height();
        mapToEntity(heightDTO, height);
        return heightRepository.save(height).getId();
    }

    public void update(final Long id, final HeightDTO heightDTO) {
        final Height height = heightRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(heightDTO, height);
        heightRepository.save(height);
    }

    public void delete(final Long id) {
        heightRepository.deleteById(id);
    }

    private HeightDTO mapToDTO(final Height height, final HeightDTO heightDTO) {
        heightDTO.setId(height.getId());
        heightDTO.setPatientNumber(height.getPatientNumber());
        heightDTO.setValueInCm(height.getValueInCm());
        return heightDTO;
    }

    private Height mapToEntity(final HeightDTO heightDTO, final Height height) {
        height.setPatientNumber(heightDTO.getPatientNumber());
        height.setValueInCm(heightDTO.getValueInCm());
        return height;
    }

}
