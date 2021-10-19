package com.baze.skole.command.smjer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SmjerCommand {
    @PositiveOrZero(message = "id must be a positive number or zero")
    private Long id;
    @NotNull(message = "naziv must not be null")
    @NotBlank(message = "naziv must not be blank")
    private String naziv;
    @NotNull(message = "idUstanova must not be null")
    private Long idUstanova;
}
