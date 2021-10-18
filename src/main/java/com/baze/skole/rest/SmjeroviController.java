package com.baze.skole.rest;

import com.baze.skole.dto.smjerovi.SmjerDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.service.smjerovi.SmjerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/smjerovi")
@CrossOrigin(origins = "http://localhost:4200")
public class SmjeroviController {

    private final SmjerService smjerService;

    public SmjeroviController(SmjerService smjerService) {
        this.smjerService = smjerService;
    }

    @GetMapping
    List<SmjerDTO> findSmjerByUstanovaId(@RequestParam(name = "idUstanova") Long idUstanova) throws ResourceNotFoundException {
        return this.smjerService.findSmjerByUstanovaId(idUstanova);
    }

}
