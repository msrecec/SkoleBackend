package com.baze.skole.command.studenti;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class StudentCommand {

    @PositiveOrZero(message = "student id must be a positive number or zero")
    private Integer id;
    @NotNull(message = "student jmbag must not be null")
    @NotBlank(message = "stutent jmbag must not be blank")
    @Size(min = 10, max = 10, message = "student jmbag must be 10 characters long")
    private String jmbag;
    @NotNull(message = "student ime must not be null")
    @NotBlank(message = "student ime must not be blank")
    @Size(min = 1, max = 50, message = "student ime must be between 1 and 50 characters long")
    private String ime;
    @NotNull(message = "student prezime must not be null")
    @NotBlank(message = "student prezime must not be blank")
    @Size(min = 1, max = 50, message = "student prezime must be between 1 and 50 characters long")
    private String prezime;
    @NotNull(message = "student datum upisa must not be null")
    private Date datumUpisa;
    @NotNull(message = "postBrPrebivalista must not be null")
    @NotBlank(message = "postBrPrebivalista must not be blank")
    @Size(min = 11, max = 11, message = "postBrPrebivalista must be 11 characters long")
    private String postBrPrebivalista;
    @NotNull(message = "postBrStanovanja must not be null")
    @NotBlank(message = "postBrStanovanja must not be blank")
    @Size(min = 11, max = 11, message = "postBrStanovanja must be 11 characters long")
    private String postBrStanovanja;

}
