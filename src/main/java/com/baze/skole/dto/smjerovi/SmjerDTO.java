package com.baze.skole.dto.smjerovi;

import com.baze.skole.dto.mjesta.MjestoDTO;
import com.baze.skole.dto.ustanove.UstanovaDTO;
import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.ustanove.Ustanova;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SmjerDTO {
    private Long id;
    private String naziv;
    private UstanovaDTO ustanova;
}
