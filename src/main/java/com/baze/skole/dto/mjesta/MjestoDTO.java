package com.baze.skole.dto.mjesta;

import com.baze.skole.dto.zupanije.ZupanijaDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MjestoDTO {
    private Long id;
    private Integer postBr;
    private String nazivMjesta;
    private ZupanijaDTO zupanija;
}
