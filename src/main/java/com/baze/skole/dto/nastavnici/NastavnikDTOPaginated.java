package com.baze.skole.dto.nastavnici;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NastavnikDTOPaginated {
    private List<NastavnikDTO> nastavnici;
    long totalPages;
    long totalElements;
}
