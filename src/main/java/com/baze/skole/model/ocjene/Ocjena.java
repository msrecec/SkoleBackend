package com.baze.skole.model.ocjene;

import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.model.studenti.Student;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ocjena", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Ocjena {
    @SequenceGenerator(name = "ocjene_sequence", sequenceName = "ocjene_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ocjene_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "datum_polaganja")
    private Date datumPolaganja;
    @Column(name = "vrijeme_polaganja")
    private LocalDateTime vrijemePolaganja;
    @Column(name = "ocjena")
    private Integer ocjena;
    @ManyToOne()
    @JoinColumn(name = "id_student", referencedColumnName = "id")
    private Student student;
    @ManyToOne()
    @JoinColumn(name = "id_kolegij", referencedColumnName = "id")
    private Kolegij kolegij;
}
