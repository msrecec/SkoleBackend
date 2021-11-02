package com.baze.skole.service.izvrsitelji;

import com.baze.skole.dto.izvrsitelji.IzvrsiteljDTO;
import com.baze.skole.exception.BadRequestException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.izvrsitelji.IzvrsiteljMapper;
import com.baze.skole.model.izvrsitelji.Izvrsitelj;
import com.baze.skole.repository.izvrsitelji.IzvrsiteljRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IzvrsiteljServiceImpl implements IzvrsiteljService{

    private IzvrsiteljMapper izvrsiteljMapper;
    private IzvrsiteljRepositoryJpa izvrsiteljRepositoryJpa;

    public IzvrsiteljServiceImpl(IzvrsiteljMapper izvrsiteljMapper, IzvrsiteljRepositoryJpa izvrsiteljRepositoryJpa) {
        this.izvrsiteljMapper = izvrsiteljMapper;
        this.izvrsiteljRepositoryJpa = izvrsiteljRepositoryJpa;
    }

    @Override
    public Optional<IzvrsiteljDTO> findByIdNastavnikAndIdKolegij(Long idNastavnik, Long idKolegij) throws BadRequestException, ResourceNotFoundException {

        if(idNastavnik < 0 || idKolegij < 0) {
            throw new BadRequestException("Bad url parameters");
        }

        List<Izvrsitelj> izvrsitelji = izvrsiteljRepositoryJpa.findIzvrsiteljByIdNastavnikAndIdKolegij(idNastavnik, idKolegij);

        if(izvrsitelji.isEmpty()) {
            throw new ResourceNotFoundException("Izvrsitelj with the given id nastavnik and id kolegij was not found");
        }

        Izvrsitelj izvrsitelj = izvrsitelji.get(0);


        return Optional.ofNullable(izvrsitelj).map(izvrsiteljMapper::mapIzvrsiteljToDTO);
    }
}
