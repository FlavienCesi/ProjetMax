package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long>{
    
}
