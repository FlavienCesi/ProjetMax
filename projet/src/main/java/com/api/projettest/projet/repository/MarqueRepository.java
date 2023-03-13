package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Marque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarqueRepository extends JpaRepository<Marque, Long>{
    Marque findByMarque(String marque);
}