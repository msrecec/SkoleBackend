package com.baze.skole.rest.controller;

import com.baze.skole.command.nastavnici.NastavnikCommand;
import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.dto.nastavnici.NastavnikDTOPaginated;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.nastavnici.NastavnikService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/nastavnici")
@CrossOrigin(origins = "http://localhost:4200")
public class NastavniciController {

    private final NastavnikService nastavnikService;

    public NastavniciController(NastavnikService nastavnikService) {
        this.nastavnikService = nastavnikService;
    }

    @GetMapping
    List<NastavnikDTO> findAllNastavnici() throws ResourceNotFoundException {
        return this.nastavnikService.findAll();
    }

    @GetMapping("/id/{id}")
    ResponseEntity<NastavnikDTO> findNastavnikById(@PathVariable(name = "id") Long id) {
        return this.nastavnikService.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    ResponseEntity<NastavnikDTOPaginated> findNastavnikPaginated(@RequestParam(name = "page") Integer page, @RequestParam(name = "pageSize") Integer pageSize) throws BadRequestException, ResourceNotFoundException {
        return this.nastavnikService.findByPage(page, pageSize).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    ResponseEntity<NastavnikDTO> save(@Valid @RequestBody NastavnikCommand command) throws ResourceNotFoundException, InternalServerErrorException {
        return this.nastavnikService.save(command)
                .map(nastavnikDTO ->ResponseEntity.ok().body(nastavnikDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    ResponseEntity<NastavnikDTO> update(@Valid @RequestBody NastavnikCommand command) throws ResourceNotFoundException {
        return this.nastavnikService.update(command)
                .map(nastavnikDTO ->ResponseEntity.ok().body(nastavnikDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/kolegiji")
    List<NastavnikDTO> findNastavnikByIdKolegij(@RequestParam(name = "idKolegij") Long idKolegij) throws ResourceNotFoundException {
        return this.nastavnikService.findByIdKolegij(idKolegij);
    }

    @GetMapping("/fts")
    ResponseEntity<NastavnikDTOPaginated> ftsNastavnici(@RequestParam(name = "input") String input, @RequestParam(name = "page") Integer page, @RequestParam(name = "pageSize") Integer pageSize) throws BadRequestException, ResourceNotFoundException {
        return this.nastavnikService.ftsNastavnici(input, page, pageSize)
                .map((nastavnikDTOPaginated -> ResponseEntity.ok().body(nastavnikDTOPaginated)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
