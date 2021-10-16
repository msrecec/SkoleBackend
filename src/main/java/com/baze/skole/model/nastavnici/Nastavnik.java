package com.baze.skole.model.nastavnici;

import com.baze.skole.model.izvrsitelji.Izvrsitelj;
import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.zupanije.Zupanija;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nastavnici", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id"),
        @UniqueConstraint(name = "jmbg", columnNames = "jmbg")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Nastavnik {
    @SequenceGenerator(name = "nastavnici_sequence", sequenceName = "nastavnici_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nastavnici_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "jmbg")
    private String jmbg;
    @Column(name="ime")
    private String ime;
    @Column(name="prezime")
    private String prezime;
    @Column(name="adresa")
    private String adresa;
    @Column(name="titula_ispred")
    private String titulaIspred;
    @Column(name="titula_iza")
    private String titulaIza;
    @Column(name="lozinka")
    private String lozinka;
    @ManyToOne
    private Mjesto mjestoPrebivalista;
    @OneToMany(targetEntity = Izvrsitelj.class, mappedBy = "nastavnik")
    List<Izvrsitelj> izvrsitelji;
    @ManyToMany(targetEntity = Kolegij.class, mappedBy = "nastavnici")
    private List<Kolegij> kolegiji;
}
