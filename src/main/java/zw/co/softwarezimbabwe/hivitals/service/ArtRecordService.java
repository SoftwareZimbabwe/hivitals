package zw.co.softwarezimbabwe.hivitals.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.softwarezimbabwe.hivitals.domain.ArtRecord;
import zw.co.softwarezimbabwe.hivitals.model.ArtRecordDTO;
import zw.co.softwarezimbabwe.hivitals.repos.ArtRecordRepository;
import zw.co.softwarezimbabwe.hivitals.util.NotFoundException;


@Service
public class ArtRecordService {

    private final ArtRecordRepository artRecordRepository;

    public ArtRecordService(final ArtRecordRepository artRecordRepository) {
        this.artRecordRepository = artRecordRepository;
    }

    public List<ArtRecordDTO> findAll() {
        final List<ArtRecord> artRecords = artRecordRepository.findAll(Sort.by("id"));
        return artRecords.stream()
                .map((artRecord) -> mapToDTO(artRecord, new ArtRecordDTO()))
                .toList();
    }

    public ArtRecordDTO get(final Long id) {
        return artRecordRepository.findById(id)
                .map(artRecord -> mapToDTO(artRecord, new ArtRecordDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ArtRecordDTO artRecordDTO) {
        final ArtRecord artRecord = new ArtRecord();
        mapToEntity(artRecordDTO, artRecord);
        return artRecordRepository.save(artRecord).getId();
    }

    public void update(final Long id, final ArtRecordDTO artRecordDTO) {
        final ArtRecord artRecord = artRecordRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(artRecordDTO, artRecord);
        artRecordRepository.save(artRecord);
    }

    public void delete(final Long id) {
        artRecordRepository.deleteById(id);
    }

    private ArtRecordDTO mapToDTO(final ArtRecord artRecord, final ArtRecordDTO artRecordDTO) {
        artRecordDTO.setId(artRecord.getId());
        artRecordDTO.setPatientNmumber(artRecord.getPatientNmumber());
        artRecordDTO.setHivStatus(artRecord.getHivStatus());
        return artRecordDTO;
    }

    private ArtRecord mapToEntity(final ArtRecordDTO artRecordDTO, final ArtRecord artRecord) {
        artRecord.setPatientNmumber(artRecordDTO.getPatientNmumber());
        artRecord.setHivStatus(artRecordDTO.getHivStatus());
        return artRecord;
    }

}
