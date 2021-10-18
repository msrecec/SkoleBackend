package com.baze.skole.rest;

import com.baze.skole.dto.ulogaizvrsitelja.UlogaIzvrsiteljaDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.ulogaizvrsitelja.UlogaIzvrsiteljaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ulogaIzvrsitelja")
@CrossOrigin(origins = "http://localhost:4200")
public class UlogaIzvrsiteljaController {

    private final UlogaIzvrsiteljaService ulogaIzvrsiteljaService;

    public UlogaIzvrsiteljaController(UlogaIzvrsiteljaService ulogaIzvrsiteljaService) {
        this.ulogaIzvrsiteljaService = ulogaIzvrsiteljaService;
    }

    @GetMapping("/nastavnici")
    ResponseEntity<UlogaIzvrsiteljaDTO> findUlogaIzvrsiteljaByNastavnikId(@RequestParam(name = "nastavnikId") Long nastavnikId) throws ResourceNotFoundException {
        return this.ulogaIzvrsiteljaService.findUlogaIzvrsiteljaByNastavnikId(nastavnikId)
                .map((ulogaIzvrsiteljaDTO) -> ResponseEntity.ok().body(ulogaIzvrsiteljaDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
