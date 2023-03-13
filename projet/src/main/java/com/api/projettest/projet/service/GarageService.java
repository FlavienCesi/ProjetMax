package com.api.projettest.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.projettest.projet.model.Garage;
import com.api.projettest.projet.repository.GarageRepository;

import com.api.projettest.projet.model.Ville;
import com.api.projettest.projet.repository.VilleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GarageService {

    @Autowired
    private final GarageRepository garageRepository;
    private final VilleRepository villeRepository;

    public List<Garage> getAllGarages() {
        return garageRepository.findAll();
    }

    public Optional<Garage> getGarageById(Long id_garage) {
        return garageRepository.findById(id_garage);
    }

    public Garage addGarage(Garage garage) {
        Ville ville = villeRepository.findByVille(garage.getVille().getVille());
        if (ville == null) {
            ville = new Ville();
            ville.setVille(garage.getVille().getVille());
            ville = villeRepository.save(ville);
        }
        garage.setVille(ville);
        return garageRepository.save(garage);
    }

    public Garage updateGarage(Long id_garage, Garage garage) {
        return garageRepository.findById(id_garage)
                .map(p-> {
                    p.setNom(garage.getNom());
                    p.setMail(garage.getMail());
                    p.setNumeroTelephone(garage.getNumeroTelephone());
    
                    Ville ville = villeRepository.findByVille(garage.getVille().getVille());
                    if (ville == null) {
                        ville = new Ville();
                        ville.setVille(garage.getVille().getVille());
                        ville = villeRepository.save(ville);
                    }
                    p.setVille(ville);
    
                    return garageRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Garage non trouvé !"));
    }
    

    public String deleteGarage(Long id_garage) {
        garageRepository.deleteById(id_garage);
        return "Garage supprimé";
    }
}