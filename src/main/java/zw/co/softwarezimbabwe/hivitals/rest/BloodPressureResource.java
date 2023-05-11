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
import zw.co.softwarezimbabwe.hivitals.model.BloodPressureDTO;
import zw.co.softwarezimbabwe.hivitals.service.BloodPressureService;


@RestController
@RequestMapping(value = "/api/bloodPressures", produces = MediaType.APPLICATION_JSON_VALUE)
public class BloodPressureResource {

    private final BloodPressureService bloodPressureService;

    public BloodPressureResource(final BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    @GetMapping
    public ResponseEntity<List<BloodPressureDTO>> getAllBloodPressures() {
        return ResponseEntity.ok(bloodPressureService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodPressureDTO> getBloodPressure(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(bloodPressureService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBloodPressure(
            @RequestBody @Valid final BloodPressureDTO bloodPressureDTO) {
        final Long createdId = bloodPressureService.create(bloodPressureDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBloodPressure(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final BloodPressureDTO bloodPressureDTO) {
        bloodPressureService.update(id, bloodPressureDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBloodPressure(@PathVariable(name = "id") final Long id) {
        bloodPressureService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
