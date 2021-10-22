package com.baze.skole.command.ocjene;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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
    private String vrijemePolaganja;
    @NotNull(message = "ocjena must not be null")
    @Min(value = 1, message = "ocjena must be greater or equal to 1")
    @Max(value = 5, message = "ocijena must be lower than or equal to 5")
    private Integer ocjena;
    @NotNull(message = "student id must not be null")
    private Long studentId;
    @NotNull(message = "kolegij id must not be null")
    private Long kolegijId;
}
