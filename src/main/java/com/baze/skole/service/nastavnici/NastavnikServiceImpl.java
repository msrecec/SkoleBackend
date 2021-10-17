package com.baze.skole.service.nastavnici;

import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.dto.nastavnici.NastavnikDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.nastavnici.NastavniciMapper;
import com.baze.skole.model.error.ErrorMessageConstants;
import com.baze.skole.model.nastavnici.Nastavnik;
import com.baze.skole.repository.nastavnici.NastavniciRepositoryJpa;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NastavnikServiceImpl implements NastavnikService{

    private final NastavniciRepositoryJpa nastavniciRepositoryJpa;
    private final NastavniciMapper nastavniciMapper;
    private static final Integer MAXIMUM_PAGE_SIZE = 100;

    public NastavnikServiceImpl(NastavniciRepositoryJpa nastavniciRepositoryJpa, NastavniciMapper nastavniciMapper) {
        this.nastavniciRepositoryJpa = nastavniciRepositoryJpa;
        this.nastavniciMapper = nastavniciMapper;
    }

    @Override
    public Optional<NastavnikDTO> findById(Long id) {
        return nastavniciRepositoryJpa.findById(id).map(nastavniciMapper::mapNastavnikToDTO);
    }

    @Override
    public List<NastavnikDTO> findAll() throws ResourceNotFoundException {

        List<Nastavnik> nastavnici = nastavniciRepositoryJpa.findAll();

        if(nastavnici.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        return nastavnici.stream().map(nastavniciMapper::mapNastavnikToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<NastavnikDTOPaginated> findByPage(Integer page, Integer pageSize) throws BadParamsException, ResourceNotFoundException {

        if(page < 0 || pageSize > MAXIMUM_PAGE_SIZE) {
            throw new BadParamsException(ErrorMessageConstants.BAD_PARAMS.getMessage());
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        List<Nastavnik> nastavnici = nastavniciRepositoryJpa.findAll(pageRequest).getContent();

        if(nastavnici.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        long totalPages = nastavniciRepositoryJpa.findAll(pageRequest).getTotalPages();

        long totalElements = nastavniciRepositoryJpa.findAll(pageRequest).getTotalElements();

        return Optional.of(new NastavnikDTOPaginated(nastavnici.stream().map(nastavniciMapper::mapNastavnikToDTO).collect(Collectors.toList()), totalPages, totalElements));
    }
}
