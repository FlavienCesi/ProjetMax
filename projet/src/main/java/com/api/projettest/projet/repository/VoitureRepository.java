package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface VoitureRepository extends JpaRepository<Voiture, Long>{
    Page<Voiture> findAll(Specification<Voiture> spec, Pageable page);
}

