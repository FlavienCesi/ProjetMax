package com.api.projettest.projet.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="carburant")
public class Carburant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carburant")
    private Long id_carburant;
    @Column(name = "carburant")
    private String carburant; 
}

