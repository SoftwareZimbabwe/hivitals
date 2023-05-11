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
import zw.co.softwarezimbabwe.hivitals.model.BloodGlucoseDTO;
import zw.co.softwarezimbabwe.hivitals.service.BloodGlucoseService;


@RestController
@RequestMapping(value = "/api/bloodGlucoses", produces = MediaType.APPLICATION_JSON_VALUE)
public class BloodGlucoseResource {

    private final BloodGlucoseService bloodGlucoseService;

    public BloodGlucoseResource(final BloodGlucoseService bloodGlucoseService) {
        this.bloodGlucoseService = bloodGlucoseService;
    }

    @GetMapping
    public ResponseEntity<List<BloodGlucoseDTO>> getAllBloodGlucoses() {
        return ResponseEntity.ok(bloodGlucoseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodGlucoseDTO> getBloodGlucose(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(bloodGlucoseService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createBloodGlucose(
            @RequestBody @Valid final BloodGlucoseDTO bloodGlucoseDTO) {
        final Long createdId = bloodGlucoseService.create(bloodGlucoseDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBloodGlucose(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final BloodGlucoseDTO bloodGlucoseDTO) {
        bloodGlucoseService.update(id, bloodGlucoseDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteBloodGlucose(@PathVariable(name = "id") final Long id) {
        bloodGlucoseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
