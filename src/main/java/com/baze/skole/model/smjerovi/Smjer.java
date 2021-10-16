package com.baze.skole.model.smjerovi;

import com.baze.skole.model.kolegiji.Kolegij;
import com.baze.skole.model.ustanove.Ustanova;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "smjerovi", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Smjer {
    @SequenceGenerator(name = "smjerovi_sequence", sequenceName = "smjerovi_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "smjerovi_sequence")
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "naziv")
    private String naziv;
    @ManyToOne
    private Ustanova ustanova;
    @OneToMany(targetEntity = Kolegij.class, mappedBy = "smjer")
    List<Kolegij> kolegiji;
}
