package com.baze.skole.command.nastavnici;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class NastavnikCommand {
    @PositiveOrZero(message = "nastavnik id must be a positive number or zero")
    private Long id;
    @NotNull(message = "nastavnik jmbg must not be null")
    @NotBlank(message = "nastavnik jmbg must not be blank")
    private String jmbg;
    @NotNull(message = "nastavnik ime must not be null")
    @NotBlank(message = "nastavnik ime must not be blank")
    @Size(min = 1, max = 50, message = "nastavnik ime must be between 1 and 50 characters long")
    private String ime;
    @NotNull(message = "nastavnik prezime must not be null")
    @NotBlank(message = "nastavnik prezime must not be blank")
    @Size(min = 1, max = 50, message = "nastavnik prezime must be between 1 and 50 characters long")
    private String prezime;
    @NotNull(message = "nastavnik adresa must not be null")
    @NotBlank(message = "nastavnik adresa must not be blank")
    @Size(min = 1, max = 100, message = "nastavnik adresa must be between 1 and 100 characters long")
    private String adresa;
    @NotNull(message = "nastavnik titula ispred must not be null")
    @NotBlank(message = "nastavnik titula ispred must not be blank")
    @Size(min = 1, max = 30, message = "nastavnik titula ispred must be between 1 and 30 characters long")
    private String titulaIspred;
    @NotNull(message = "nastavnik titula iza must not be null")
    @NotBlank(message = "nastavnik titula iza must not be blank")
    @Size(min = 1, max = 30, message = "nastavnik titula iza must be between 1 and 30 characters long")
    private String titulaIza;
    @NotNull(message = "nastavnik lozinka iza must not be null")
    @NotBlank(message = "nastavnik lozinka iza must not be blank")
    @Size(min = 1, max = 32, message = "nastavnik lozinka iza must be between 1 and 32 characters long")
    private String lozinka;
    @NotNull(message = "postBr must not be null")
    private Integer postBr;

}
