package com.baze.skole.service.zupanije;

import com.baze.skole.dto.zupanije.ZupanijaDTO;
import com.baze.skole.exception.ResourceNotFoundException;
import com.baze.skole.mapping.mapper.zupanije.ZupanijeMapper;
import com.baze.skole.model.error.ErrorMessageConstants;
import com.baze.skole.model.zupanije.Zupanija;
import com.baze.skole.repository.zupanije.ZupanijeRepositoryJpa;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ZupanijeServiceImpl implements ZupanijeService{
    ZupanijeMapper mapper;
    ZupanijeRepositoryJpa zupanijeRepositoryJpa;

    public ZupanijeServiceImpl(ZupanijeMapper mapper, ZupanijeRepositoryJpa zupanijeRepositoryJpa) {
        this.mapper = mapper;
        this.zupanijeRepositoryJpa = zupanijeRepositoryJpa;
    }

    @Override
    public List<ZupanijaDTO> findAll() throws ResourceNotFoundException {

        List<Zupanija> zupanije = zupanijeRepositoryJpa.findAll();

        if(zupanije.isEmpty()) {
            throw new ResourceNotFoundException(ErrorMessageConstants.RESOURCE_NOT_FOUND.getMessage());
        }

        return zupanije.stream().map(mapper::mapZupanijaToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ZupanijaDTO> findById(Long id) {
        return zupanijeRepositoryJpa.findById(id).map(mapper::mapZupanijaToDTO);
    }
}
