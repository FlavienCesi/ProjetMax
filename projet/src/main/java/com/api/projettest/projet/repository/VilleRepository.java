package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, Long>{
    Ville findByVille(String ville);
}
