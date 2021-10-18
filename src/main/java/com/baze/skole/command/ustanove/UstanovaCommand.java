package com.baze.skole.command.ustanove;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UstanovaCommand {
    @NotNull(message = "ustanova id must not be null")
    @PositiveOrZero(message = "ustanova id must be a positive number or zero")
    private Long id;
    @NotNull(message = "ustanova oib must not be null")
    @NotBlank(message = "ustanova oib must not be blank")
    private String oib;
    @NotNull(message = "ustanova naziv must not be null")
    @NotBlank(message = "ustanova naziv must not be blank")
    private String naziv;
    @NotNull(message = "ustanova zRacun must not be null")
    @NotBlank(message = "ustanova zRacun must not be blank")
    private String zRacun;
    @NotNull(message = "ustanova adresa must not be null")
    @NotBlank(message = "ustanova adresa must not be blank")
    private String adresa;
    @NotNull(message = "ustanova datum osnutka must not be null")
    private Date datumOsnutka;
    @NotNull(message = "ustanova postbr must not be null")
    @NotBlank(message = "ustanova postbr must not be blank")
    private String postbr;
}
