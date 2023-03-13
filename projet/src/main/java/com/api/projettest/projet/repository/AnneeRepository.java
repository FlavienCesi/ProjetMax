package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Annee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnneeRepository extends JpaRepository<Annee, Long>{
    Annee findByAnnee(Integer annee);
}

