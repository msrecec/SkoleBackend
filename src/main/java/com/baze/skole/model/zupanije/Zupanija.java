package com.baze.skole.model.zupanije;

import com.baze.skole.model.mjesta.Mjesto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zupanije", schema = "public", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = "id"),
        @UniqueConstraint(name = "naziv_zupanija", columnNames = "naziv_zupanija")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Zupanija {
    @SequenceGenerator(name = "zupanije_sequence", sequenceName = "zupanije_sequence", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zupanije_sequence")
    @Column(name = "id")
    @Id
    private Long id;
    @Column(name = "naziv_zupanija")
    private String nazivZupanija;
    @OneToMany(targetEntity = Mjesto.class, mappedBy = "zupanija")
    List<Mjesto> mjesta;
}
