package com.baze.skole.dto.studenti;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StudentDTOPaginated {
    List<StudentDTO> students;
    long totalPages;
    long totalElements;
}
