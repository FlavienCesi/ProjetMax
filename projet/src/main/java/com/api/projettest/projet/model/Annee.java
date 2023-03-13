package com.api.projettest.projet.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="annee")
public class Annee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_annee")
    private Long id_annee;
    @Column(name = "annee")
    private Integer annee;  
}
