package com.baze.skole.rest;

import com.baze.skole.dto.izvrsitelji.IzvrsiteljDTO;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.izvrsitelji.IzvrsiteljService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/izvrsitelji")
@CrossOrigin(origins = "http://localhost:4200")
public class IzvrsiteljiController {

    private IzvrsiteljService izvrsiteljService;

    public IzvrsiteljiController(IzvrsiteljService izvrsiteljService) {
        this.izvrsiteljService = izvrsiteljService;
    }

    @GetMapping("/nastavnici/kolegiji")
    ResponseEntity<IzvrsiteljDTO> findIzvrsiteljByIdNastavnikAndIdKolegij(
            @RequestParam(name = "nastavnikId") Long nastavnikId,
            @RequestParam(name = "kolegijId") Long kolegijId
    ) throws BadRequestException, ResourceNotFoundException {
        return izvrsiteljService.findByIdNastavnikAndIdKolegij(nastavnikId, kolegijId)
                .map(
                        izvrsiteljDTO -> ResponseEntity.ok().body(izvrsiteljDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

}
