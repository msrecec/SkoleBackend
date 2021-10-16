package com.baze.skole.rest;

import com.baze.skole.dto.zupanije.ZupanijaDTO;
import com.baze.skole.service.zupanije.ZupanijeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/zupanije")
@CrossOrigin(origins = "http://localhost:4200")
public class ZupanijeController {

    private ZupanijeService zupanijeService;

    public ZupanijeController(ZupanijeService zupanijeService) {
        this.zupanijeService = zupanijeService;
    }

    @GetMapping
    public List<ZupanijaDTO> findAllZupanije() {
        return this.zupanijeService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ZupanijaDTO> findZupanijaById(@PathVariable final Long id) {
        return zupanijeService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

}
