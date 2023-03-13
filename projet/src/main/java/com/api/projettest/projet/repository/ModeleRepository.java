package com.api.projettest.projet.repository;

import com.api.projettest.projet.model.Modele;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeleRepository extends JpaRepository<Modele, Long>{
    Modele findByModele(String modele);
}
