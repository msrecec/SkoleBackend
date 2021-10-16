package com.baze.skole.model.ulogaizvrsitelja;

import com.baze.skole.model.izvrsitelji.Izvrsitelj;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uloga_izvrsitelja", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UlogaIzvrsitelja {
    @SequenceGenerator(name = "uloga_izvrsitelja_sequence", sequenceName = "uloga_izvrsitelja_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uloga_izvrsitelja_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(targetEntity = Izvrsitelj.class, mappedBy = "ulogaIzvrsitelja")
    private List<Izvrsitelj> izvrsitelji;
}
