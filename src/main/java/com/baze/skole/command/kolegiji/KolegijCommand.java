package com.baze.skole.command.kolegiji;

import com.baze.skole.dto.smjerovi.SmjerDTO;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class KolegijCommand {
    @PositiveOrZero(message = "kolegij id must be a positive number or zero")
    private Long id;
    @NotNull(message = "kolegij smjer must not be null")
    @NotBlank(message = "kolegij smjer must not be blank")
    private String naziv;
    @NotNull(message = "kolegij opis must not be null")
    @NotBlank(message = "kolegij opis must not be blank")
    private String opis;
    @NotNull(message = "smjer id must not be null")
    private Long smjerId;
}
