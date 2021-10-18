package com.baze.skole.dto.ustanove;

import com.baze.skole.dto.mjesta.MjestoDTO;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UstanovaDTO {
    private Long id;
    private String oib;
    private String naziv;
    private String ziroRacun;
    private String adresa;
    private Date datumOsnutka;
    private MjestoDTO mjesto;
}
