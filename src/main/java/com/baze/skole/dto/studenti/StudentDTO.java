package com.baze.skole.dto.studenti;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.dto.smjerovi.SmjerDTO;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StudentDTO {
    private Long id;
    private String jmbag;
    private String ime;
    private String prezime;
    private Date datumUpisa;
    private MjestoDTO mjestoPrebivalista;
    private MjestoDTO mjestoStanovanja;
    private SmjerDTO smjerDTO;
}
