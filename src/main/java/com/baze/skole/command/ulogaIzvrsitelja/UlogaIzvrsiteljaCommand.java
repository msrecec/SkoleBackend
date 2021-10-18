package com.baze.skole.command.ulogaIzvrsitelja;

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
public class UlogaIzvrsiteljaCommand {
    @PositiveOrZero(message = "uloga izvrsitelja id must be a positive number or zero")
    private Long id;
    @NotNull(message = "uloga izvrsitelja naziv must not be null")
    @NotBlank(message = "uloga izvrsitelja naziv must not be blank")
    private String naziv;
}
