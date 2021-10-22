package com.baze.skole.dto.ocjene;

import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.dto.studenti.StudentDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OcjenaDTO {
    private Long id;
    private Date datumPolaganja;
    private OffsetDateTime vrijemePolaganja;
    private Integer ocjena;
    private StudentDTO student;
    private KolegijDTO kolegij;
}
