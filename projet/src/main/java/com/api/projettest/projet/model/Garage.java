package com.api.projettest.projet.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="garage")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_garage")
    private Long id_garage;
    @Column(name = "nom")
    private String nom;
    @Column(name = "numero_telephone")
    private String numeroTelephone;
    @Column(name = "mail")
    private String mail;
    @ManyToOne
    @JoinColumn(name = "id_ville")
    private Ville ville;
}