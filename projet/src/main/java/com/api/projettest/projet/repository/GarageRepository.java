package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Garage;
import com.api.projettest.projet.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GarageRepository extends JpaRepository<Garage, Long> {
    List<Garage> findByNom(String nom);
    List<Garage> findByVille(Ville ville);
    List<Garage> findByNomAndVille(String nom, Ville ville);
}
