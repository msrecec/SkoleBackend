package com.baze.skole.service.kolegiji;

import com.baze.skole.command.kolegiji.KolegijCommand;
import com.baze.skole.command.smjer.SmjerCommand;
import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.exception.InternalServerErrorException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.kolegiji.KolegijiMapper;
import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.model.smjerovi.Smjer;
import com.baze.skole.repository.kolegiji.KolegijRepositoryJpa;
import com.baze.skole.repository.smjerovi.SmjerRepositoryJpa;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KolegijServiceImpl implements KolegijService{

    private final KolegijRepositoryJpa kolegijRepositoryJpa;
    private final SmjerRepositoryJpa smjerRepositoryJpa;
    private final KolegijiMapper kolegijiMapper;

    public KolegijServiceImpl(KolegijRepositoryJpa kolegijRepositoryJpa, SmjerRepositoryJpa smjerRepositoryJpa, KolegijiMapper kolegijiMapper) {
        this.kolegijRepositoryJpa = kolegijRepositoryJpa;
        this.smjerRepositoryJpa = smjerRepositoryJpa;
        this.kolegijiMapper = kolegijiMapper;
    }

    @Override
    public List<KolegijDTO> findKolegijBySmjer(Long idSmjer) throws ResourceNotFoundException {

        List<Kolegij> kolegiji = kolegijRepositoryJpa.findKolegijiByIdSmjera(idSmjer);

        if(kolegiji.isEmpty()) {
            throw new ResourceNotFoundException("kolegiji were not found");
        }

        return kolegiji.stream().map(kolegijiMapper::mapKolegijToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<KolegijDTO> saveKolegijBySmjerId(KolegijCommand command) throws ResourceNotFoundException, InternalServerErrorException {

        Kolegij kolegij = Kolegij.builder()
                .naziv(command.getNaziv())
                .opis(command.getOpis())
                .build();

        Optional<Smjer> smjer = smjerRepositoryJpa.findById(command.getSmjerId());

        if(smjer.isEmpty()) {
            throw new ResourceNotFoundException("smjer with the given id was not found");
        }

        kolegij.setSmjer(smjer.get());

        kolegij = kolegijRepositoryJpa.save(kolegij);

        if(kolegij == null) {
            throw new InternalServerErrorException("there was an error when saving kolegij to the db");
        }

        return Optional.of(kolegij).map(kolegijiMapper::mapKolegijToDTO);
    }
}
