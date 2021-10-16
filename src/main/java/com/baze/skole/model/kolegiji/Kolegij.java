package com.baze.skole.model.kolegiji;

import com.baze.skole.model.ocjene.Ocjena;
import com.baze.skole.model.smjerovi.Smjer;
import com.baze.skole.model.studenti.Student;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kolegiji", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Kolegij {
    @SequenceGenerator(name = "kolegiji_sequence", sequenceName = "kolegiji_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kolegiji_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "opis")
    private String opis;
    @ManyToOne
    private Smjer smjer;
    @OneToMany(targetEntity = Ocjena.class, mappedBy = "kolegij")
    List<Ocjena> ocjene;
    @ManyToMany(targetEntity = Student.class, mappedBy = "kolegiji")
    private List<Student> studenti;
}
