package com.baze.skole.rest;

import com.baze.skole.command.kolegiji.KolegijCommand;
import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.service.kolegiji.KolegijService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/kolegiji")
@CrossOrigin(origins = "http://localhost:4200")
public class KolegijiController {

    private final KolegijService kolegijService;

    public KolegijiController(KolegijService kolegijService) {
        this.kolegijService = kolegijService;
    }

    @GetMapping
    List<KolegijDTO> findKolegijByIdSmjer(@RequestParam("idSmjer") final Long idSmjer) throws ResourceNotFoundException {
        return this.kolegijService.findKolegijBySmjer(idSmjer);
    }

    @PostMapping
    ResponseEntity<KolegijDTO> saveKolegijByIdSmjer(@RequestBody @Valid final KolegijCommand command) throws InternalServerErrorException, ResourceNotFoundException {
        return this.kolegijService.saveKolegijBySmjerId(command)
                .map(kolegijDTO -> ResponseEntity.ok().body(kolegijDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
