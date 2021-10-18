package com.baze.skole.service.ustanove;

import com.baze.skole.command.ustanove.UstanovaCommand;
import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.dto.ustanove.UstanovaDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.InternalServerError;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.ustanove.UstanoveMapper;
import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.ustanove.Ustanova;
import com.baze.skole.repository.mjesta.MjestaRepositoryJpa;
import com.baze.skole.repository.ustanove.UstanoveRepositoryJpa;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UstanovaServiceImpl implements UstanovaService {

    private UstanoveMapper ustanoveMapper;

    private UstanoveRepositoryJpa ustanoveRepositoryJpa;
    private MjestaRepositoryJpa mjestaRepositoryJpa;
    private static final Integer MAXIMUM_PAGE_SIZE = 100;

    public UstanovaServiceImpl(UstanoveMapper ustanoveMapper, UstanoveRepositoryJpa ustanoveRepositoryJpa, MjestaRepositoryJpa mjestaRepositoryJpa) {
        this.ustanoveMapper = ustanoveMapper;
        this.ustanoveRepositoryJpa = ustanoveRepositoryJpa;
        this.mjestaRepositoryJpa = mjestaRepositoryJpa;
    }

    @Override
    public List<UstanovaDTO> findAll() throws ResourceNotFoundException {
        List<Ustanova> ustanove = ustanoveRepositoryJpa.findAll();

        if(ustanove.isEmpty()) {
            throw new ResourceNotFoundException("ustanove were not found");
        }

        return ustanove.stream().map(ustanoveMapper::mapUstanovaToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UstanovaDTO> findById(Long id) throws ResourceNotFoundException {

        Optional<Ustanova> ustanova = ustanoveRepositoryJpa.findById(id);

        if(ustanova.isEmpty()) {
            throw new ResourceNotFoundException("ustanova with the given id was not found");
        }

        return ustanova.map(ustanoveMapper::mapUstanovaToDTO);
    }

    @Override
    public Optional<UstanovaDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadParamsException, ResourceNotFoundException {

        if(page < 0 || pageSize > 100) {
            throw new BadParamsException("page should not be smaller than 0 and pageSize should not be larger than 100");
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        List<Ustanova> ustanove = ustanoveRepositoryJpa.findAll(pageRequest).getContent();

        if(ustanove.isEmpty()) {
            throw new ResourceNotFoundException("ustanove were not found");
        }

        long totalPages = ustanoveRepositoryJpa.findAll(pageRequest).getTotalPages();

        long totalElements = ustanoveRepositoryJpa.findAll(pageRequest).getTotalElements();

        return Optional.of(new UstanovaDTOPaginated(ustanove.stream().map(ustanoveMapper::mapUstanovaToDTO).collect(Collectors.toList()), totalPages, totalElements));
    }

    @Override
    public Optional<UstanovaDTO> save(UstanovaCommand command) throws ResourceNotFoundException, InternalServerError {

        List<Mjesto> mjesto = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostbr());

        if(mjesto.isEmpty()) {
            throw new ResourceNotFoundException("mjesto with the given postbr was not found");
        }

        Ustanova ustanova = Ustanova.builder()
                .oib(command.getOib())
                .naziv(command.getNaziv())
                .ziroRacun(command.getZiroRacun())
                .adresa(command.getAdresa())
                .datumOsnutka(command.getDatumOsnutka())
                .mjesto(mjesto.get(0))
                .build();

        ustanova = ustanoveRepositoryJpa.save(ustanova);

        if(ustanova == null) {
            throw new InternalServerError("there was an error when saving ustanova to the db");
        }

        return Optional.of(ustanova).map(ustanoveMapper::mapUstanovaToDTO);
    }

    @Override
    public Optional<UstanovaDTO> update(UstanovaCommand command) throws ResourceNotFoundException, InternalServerError {

        Optional<Ustanova> ustanova = ustanoveRepositoryJpa.findById(command.getId());

        if(ustanova.isEmpty()) {
            throw new ResourceNotFoundException("ustanova resource was not found");
        }

        List<Mjesto> mjesto = mjestaRepositoryJpa.findMjestoByPostbr(command.getPostbr());

        if(mjesto.isEmpty()) {
            throw new ResourceNotFoundException("mjesto resource was not found");
        }

        ustanova.get().setOib(command.getOib());
        ustanova.get().setNaziv(command.getNaziv());
        ustanova.get().setZiroRacun(command.getZiroRacun());
        ustanova.get().setAdresa(command.getAdresa());
        ustanova.get().setDatumOsnutka(command.getDatumOsnutka());
        ustanova.get().setMjesto(mjesto.get(0));

        ustanova = Optional.ofNullable(ustanoveRepositoryJpa.save(ustanova.get()));

        if(ustanova.isEmpty()) {
            throw new InternalServerError("there was an error on the server");
        }

        return ustanova.map(ustanoveMapper::mapUstanovaToDTO);
    }
}
