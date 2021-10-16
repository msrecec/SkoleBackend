package com.baze.skole.model.studenti;


import com.baze.skole.model.mjesta.Mjesto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "studenti", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id"),
        @UniqueConstraint(name = "jmbag", columnNames = "jmbag")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Student {
    @SequenceGenerator(name = "studenti_sequence", sequenceName = "studenti_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studenti_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "jmbag")
    private String jmbag;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "datum_upisa")
    private Date datumUpisa;
    @ManyToOne()
    @JoinColumn(name = "id_mjesto_preb", referencedColumnName = "id")
    private Mjesto mjestoPrebivalista;
    @ManyToOne()
    @JoinColumn(name = "id_mjesto_stan", referencedColumnName = "id")
    private Mjesto mjestoStanovanja;
}
