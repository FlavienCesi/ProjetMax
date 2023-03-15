package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Garage;
import com.api.projettest.projet.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface GarageRepository extends JpaRepository<Garage, Long> {
    Page<Garage> findByNomStartingWith(String nom, Pageable page);
    Page<Garage> findByVille(Ville ville, Pageable page);
    Page<Garage> findByNomStartingWithAndVille(String nom, Ville ville, Pageable page);
}