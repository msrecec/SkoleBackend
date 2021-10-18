package com.baze.skole.rest;

import com.baze.skole.command.ustanove.UstanovaCommand;
import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.dto.ustanove.UstanovaDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.InternalServerError;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.ustanove.UstanovaService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/ustanove")
@CrossOrigin(origins = "http://localhost:4200")
public class UstanovaController {

    private UstanovaService ustanovaService;

    public UstanovaController(UstanovaService ustanovaService) {
        this.ustanovaService = ustanovaService;
    }

    @GetMapping
    List<UstanovaDTO> findAllUstanove() throws ResourceNotFoundException {
        return this.ustanovaService.findAll();
    }

    @GetMapping("/id/{id}")
    ResponseEntity<UstanovaDTO> findUstanovaById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return this.ustanovaService.findById(id)
                .map(ustanovaDTO -> ResponseEntity.ok().body(ustanovaDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    ResponseEntity<UstanovaDTOPaginated> findUstanovaByPage(@RequestParam(name = "page") Integer page, @RequestParam(name = "pageSize") Integer pageSize) throws BadParamsException, ResourceNotFoundException {
        return this.ustanovaService.findByPage(page, pageSize)
                .map(ustanovaDTOPaginated -> ResponseEntity.ok().body(ustanovaDTOPaginated))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<UstanovaDTO> saveUstanova(@Valid @RequestBody UstanovaCommand command) throws ResourceNotFoundException, InternalServerError {
        return this.ustanovaService.save(command)
                .map(ustanovaDTO -> ResponseEntity.ok().body(ustanovaDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    ResponseEntity<UstanovaDTO> updateUstanova(@Valid @RequestBody UstanovaCommand command) throws ResourceNotFoundException, InternalServerError {
        return this.ustanovaService.save(command)
                .map(ustanovaDTO -> ResponseEntity.ok().body(ustanovaDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
