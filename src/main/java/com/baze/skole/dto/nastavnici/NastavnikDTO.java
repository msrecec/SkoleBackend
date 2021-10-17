package com.baze.skole.dto.nastavnici;

import com.baze.skole.dto.mjesta.MjestoDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NastavnikDTO {
    private Long id;
    private String jmbg;
    private String ime;
    private String prezime;
    private String adresa;
    private String titulaIspred;
    private String titulaIza;
    private MjestoDTO mjestoPrebivalista;
}
