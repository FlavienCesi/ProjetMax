package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Carburant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarburantRepository extends JpaRepository<Carburant, Long>{
    Carburant findByCarburant(String carburant);
}