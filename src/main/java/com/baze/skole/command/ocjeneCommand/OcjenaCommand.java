package com.baze.skole.command.ocjeneCommand;

import com.baze.skole.dto.kolegiji.KolegijDTO;
import com.baze.skole.dto.studenti.StudentDTO;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OcjenaCommand {
    @PositiveOrZero(message = "ocjena id must be a positive number or zero")
    private Long id;
    @NotNull(message = "datum polaganja must not be null")
    private Date datumPolaganja;
    @NotNull(message = "vrijeme polaganja must not be null")
    private LocalDateTime vrijemePolaganja;
    @NotNull(message = "ocjena must not be null")
    @Size(min = 1, max = 5, message = "ocijena must be between 1 and 5 (including)")
    private Integer ocjena;
    @NotNull(message = "student id must not be null")
    private Long studentId;
    @NotNull(message = "kolegij id must not be null")
    private Long kolegijId;
}
