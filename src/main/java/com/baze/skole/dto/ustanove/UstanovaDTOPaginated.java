package com.baze.skole.dto.ustanove;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UstanovaDTOPaginated {
    List<UstanovaDTO> ustanove;
    long totalPages;
    long totalElements;
}
