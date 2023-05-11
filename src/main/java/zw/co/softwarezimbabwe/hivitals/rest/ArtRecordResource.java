package zw.co.softwarezimbabwe.hivitals.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.softwarezimbabwe.hivitals.model.ArtRecordDTO;
import zw.co.softwarezimbabwe.hivitals.service.ArtRecordService;


@RestController
@RequestMapping(value = "/api/artRecords", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtRecordResource {

    private final ArtRecordService artRecordService;

    public ArtRecordResource(final ArtRecordService artRecordService) {
        this.artRecordService = artRecordService;
    }

    @GetMapping
    public ResponseEntity<List<ArtRecordDTO>> getAllArtRecords() {
        return ResponseEntity.ok(artRecordService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtRecordDTO> getArtRecord(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(artRecordService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createArtRecord(
            @RequestBody @Valid final ArtRecordDTO artRecordDTO) {
        final Long createdId = artRecordService.create(artRecordDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArtRecord(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ArtRecordDTO artRecordDTO) {
        artRecordService.update(id, artRecordDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteArtRecord(@PathVariable(name = "id") final Long id) {
        artRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
