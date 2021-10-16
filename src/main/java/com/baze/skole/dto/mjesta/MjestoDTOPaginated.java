package com.baze.skole.dto.mjesta;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MjestoDTOPaginated {
    List<MjestoDTO> mjestoDTOs;
    long totalPages;
    long totalElements;
}
