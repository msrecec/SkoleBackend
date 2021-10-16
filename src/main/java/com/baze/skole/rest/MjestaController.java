package com.baze.skole.rest;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.service.mjesta.MjestoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/mjesta")
@CrossOrigin(origins = "http://localhost:4200")
public class MjestaController {

    MjestoService mjestoService;

    public MjestaController(MjestoService mjestoService) {
        this.mjestoService = mjestoService;
    }

    @GetMapping
    List<MjestoDTO> findAllMjesta() {
        return this.mjestoService.findAll();
    }

    @GetMapping("/id/{id}")
    ResponseEntity<MjestoDTO> findMjestoById(@PathVariable(name = "id") final Long id) {
        return this.mjestoService
                .findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
