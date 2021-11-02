package com.baze.skole.dto.izvrsitelji;

import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.dto.nastavnici.NastavnikDTO;
import com.baze.skole.dto.ulogaizvrsitelja.UlogaIzvrsiteljaDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class IzvrsiteljDTO {
    private Long id;
    private NastavnikDTO nastavnik;
    private KolegijDTO kolegij;
    private UlogaIzvrsiteljaDTO ulogaIzvrsitelja;
}
