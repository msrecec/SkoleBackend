package com.baze.skole.dto.kolegiji;

import com.baze.skole.dto.smjerovi.SmjerDTO;
import com.baze.skole.model.smjerovi.Smjer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class KolegijDTO {
    private Long id;
    private String naziv;
    private String opis;
    private SmjerDTO smjer;
}
