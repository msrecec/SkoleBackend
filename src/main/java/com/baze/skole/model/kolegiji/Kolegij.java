package com.baze.skole.model.kolegiji;

import com.baze.skole.model.izvrsitelji.Izvrsitelj;
import com.baze.skole.model.nastavnici.Nastavnik;
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
    @ManyToOne()
    @JoinColumn(name = "id_smjer", referencedColumnName = "id")
    private Smjer smjer;
    @OneToMany(targetEntity = Ocjena.class, mappedBy = "kolegij")
    List<Ocjena> ocjene;
    @ManyToMany(targetEntity = Student.class, mappedBy = "kolegiji")
    private List<Student> studenti;
    @OneToMany(targetEntity = Izvrsitelj.class, mappedBy = "kolegij")
    List<Izvrsitelj> izvrsitelji;
    @ManyToMany(targetEntity = Nastavnik.class)
    @JoinTable(
            name="izvrsitelji",
            joinColumns = {@JoinColumn(name = "id_kolegij")},
            inverseJoinColumns = {@JoinColumn(name = "id_nastavnik")}
    )
    private List<Nastavnik> nastavnici;
}
