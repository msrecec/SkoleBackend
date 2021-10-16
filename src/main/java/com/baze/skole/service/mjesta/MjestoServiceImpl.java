package com.baze.skole.service.mjesta;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.dto.mjesta.MjestoDTOPaginated;
import com.baze.skole.exception.BadParamsException;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.mjesta.MjestaMapper;
import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.repository.mjesta.MjestaRepositoryJpa;
import com.baze.skole.repository.zupanije.ZupanijeRepositoryJpa;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MjestoServiceImpl implements MjestoService{

    private MjestaRepositoryJpa mjestaRepositoryJpa;
    private MjestaMapper mapper;

    public MjestoServiceImpl(MjestaRepositoryJpa mjestaRepositoryJpa, MjestaMapper mapper) {
        this.mjestaRepositoryJpa = mjestaRepositoryJpa;
        this.mapper = mapper;
    }

    @Override
    public Optional<MjestoDTO> findById(Long id) {
        return mjestaRepositoryJpa.findById(id).map(mapper::mapMjestoToDTO);
    }

    @Override
    public List<MjestoDTO> findAll() throws ResourceNotFoundException {

        List<Mjesto> mjesta = mjestaRepositoryJpa.findAll();

        if(mjesta.isEmpty()) {
            throw new ResourceNotFoundException("mjesta were not found");
        }

        return mjesta.stream().map(mapper::mapMjestoToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MjestoDTOPaginated> findByPage(Integer page, Integer pageSize) throws ResourceNotFoundException, BadParamsException {

        if(page < 0 || pageSize > 100) {
            throw new BadParamsException("bad url parameters provided");
        }

        PageRequest pageRequest = PageRequest.of(page, pageSize);

        List<Mjesto> mjesta = mjestaRepositoryJpa.findAll(pageRequest).getContent();

        if(mjesta.size() == 0) {
            throw new ResourceNotFoundException("mjesta paginated were not found");
        }

        long totalPages = mjestaRepositoryJpa.findAll(pageRequest).getTotalPages();

        long totalElements = mjestaRepositoryJpa.findAll(pageRequest).getTotalElements();

        return Optional.of(new MjestoDTOPaginated(mjesta.stream().map(mapper::mapMjestoToDTO).collect(Collectors.toList()), totalPages, totalElements));
    }
}
