package com.baze.skole.model.ustanove;

import com.baze.skole.model.mjesta.Mjesto;
import com.baze.skole.model.smjerovi.Smjer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ustanove", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id"),
        @UniqueConstraint(name = "oib", columnNames = "oib")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Ustanova {
    @SequenceGenerator(name = "ustanove_sequence", sequenceName = "ustanove_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ustanove_sequence")
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "oib")
    private String oib;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "z_racun")
    private String zRacun;
    @Column(name = "adresa")
    private String adresa;
    @Column(name = "datum_osnutka")
    private Date datumOsnutka;
    @ManyToOne()
    @JoinColumn(name = "id_mjesto", referencedColumnName = "id")
    private Mjesto mjesto;
    @OneToMany(targetEntity = Smjer.class, mappedBy = "ustanova")
    List<Smjer> smjerovi;

}
