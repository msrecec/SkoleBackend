package com.baze.skole.model.mjesta;

import com.baze.skole.model.studenti.Student;
import com.baze.skole.model.zupanije.Zupanija;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mjesta", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "postbr", columnNames = "postbr")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Mjesto {
    @SequenceGenerator(name = "mjesta_sequence", sequenceName = "mjesta_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mjesta_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "postbr")
    private String postBr;
    @Column(name = "naziv_mjesto")
    private String nazivMjesta;
    @ManyToOne
    private Zupanija zupanija;
    @OneToMany(targetEntity = Student.class, mappedBy = "mjestoPrebivalista")
    List<Student> studentiPrebivalista;
    @OneToMany(targetEntity = Student.class, mappedBy = "mjestoStanovanja")
    List<Student> studentiStanovanja;
}
