package com.api.projettest.projet.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="voiture")
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voiture")
    private Long id_voiture;
    @Column(name = "plaque")
    private String plaque;
    @Column(name = "kilometre")
    private Integer kilometre;
    @ManyToOne
    @JoinColumn(name = "id_marque")
    private Marque marque;
    @ManyToOne
    @JoinColumn(name = "id_modele")
    private Modele modele;
    @ManyToOne
    @JoinColumn(name = "id_annee")
    private Annee annee;
    @ManyToOne
    @JoinColumn(name = "id_carburant")
    private Carburant carburant;
    @ManyToOne
    @JoinColumn(name = "id_garage", nullable = true)
    private Garage garage;
}
