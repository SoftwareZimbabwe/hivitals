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
import zw.co.softwarezimbabwe.hivitals.model.HeightDTO;
import zw.co.softwarezimbabwe.hivitals.service.HeightService;


@RestController
@RequestMapping(value = "/api/heights", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeightResource {

    private final HeightService heightService;

    public HeightResource(final HeightService heightService) {
        this.heightService = heightService;
    }

    @GetMapping
    public ResponseEntity<List<HeightDTO>> getAllHeights() {
        return ResponseEntity.ok(heightService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeightDTO> getHeight(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(heightService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createHeight(@RequestBody @Valid final HeightDTO heightDTO) {
        final Long createdId = heightService.create(heightDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHeight(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final HeightDTO heightDTO) {
        heightService.update(id, heightDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHeight(@PathVariable(name = "id") final Long id) {
        heightService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
