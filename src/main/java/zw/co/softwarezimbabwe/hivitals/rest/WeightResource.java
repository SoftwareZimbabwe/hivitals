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
import zw.co.softwarezimbabwe.hivitals.model.WeightDTO;
import zw.co.softwarezimbabwe.hivitals.service.WeightService;


@RestController
@RequestMapping(value = "/api/weights", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeightResource {

    private final WeightService weightService;

    public WeightResource(final WeightService weightService) {
        this.weightService = weightService;
    }

    @GetMapping
    public ResponseEntity<List<WeightDTO>> getAllWeights() {
        return ResponseEntity.ok(weightService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeightDTO> getWeight(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(weightService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createWeight(@RequestBody @Valid final WeightDTO weightDTO) {
        final Long createdId = weightService.create(weightDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateWeight(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final WeightDTO weightDTO) {
        weightService.update(id, weightDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteWeight(@PathVariable(name = "id") final Long id) {
        weightService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
