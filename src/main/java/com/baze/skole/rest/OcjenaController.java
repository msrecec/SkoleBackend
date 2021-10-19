package com.baze.skole.rest;

import com.baze.skole.dto.ocjene.OcjenaDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.ocjene.OcjenaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ocjene")
@CrossOrigin(origins = "http://localhost:4200")
public class OcjenaController {

    private final OcjenaService ocjenaService;

    public OcjenaController(OcjenaService ocjenaService) {
        this.ocjenaService = ocjenaService;
    }

    @GetMapping("/studenti/kolegiji")
    ResponseEntity<OcjenaDTO> findOcjenaByStudentIdAndKolegijId(@RequestParam(name = "studentId") Long studentId,
                                                                @RequestParam(name = "kolegijId") Long kolegijId) throws ResourceNotFoundException {
        return ocjenaService.findOcjenaByStudentIdAndKolegijId(studentId, kolegijId)
                .map(ocjenaDTO -> ResponseEntity.ok().body(ocjenaDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}