package com.baze.skole.model.izvrsitelji;

import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.model.nastavnici.Nastavnik;
import com.baze.skole.model.ulogaizvrsitelja.UlogaIzvrsitelja;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "izvrsitelji", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Izvrsitelj {
    @SequenceGenerator(name = "izvrsitelji_sequence", sequenceName = "izvrsitelji_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "izvrsitelji_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @ManyToOne
    private Nastavnik nastavnik;
    @ManyToOne
    private Kolegij kolegij;
    @ManyToOne
    private UlogaIzvrsitelja ulogaIzvrsitelja;
}
