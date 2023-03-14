package com.api.projettest.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.projettest.projet.model.Garage;
import com.api.projettest.projet.repository.GarageRepository;

import com.api.projettest.projet.model.Ville;
import com.api.projettest.projet.repository.VilleRepository;

import com.api.projettest.projet.model.GarageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GarageService {

    @Autowired
    private final GarageRepository garageRepository;
    private final VilleRepository villeRepository;

    public GarageDto getAllGarages(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Garage> garages = garageRepository.findAll(pageable);
        long total = garages.getTotalElements();
        int totalPages = garages.getTotalPages();
        List<Garage> data = garages.getContent();
        return new GarageDto(page, perPage, total, totalPages, data);
    }

    public Optional<Garage> getGarageById(Long id_garage) {
        return garageRepository.findById(id_garage);
    }

    public GarageDto getGarageByNom(String nom, int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Garage> garages = garageRepository.findByNom(nom, pageable);
        long total = garages.getTotalElements();
        int totalPages = garages.getTotalPages();
        List<Garage> data = garages.getContent();
        return new GarageDto(page, perPage, total, totalPages, data);
    }

    public GarageDto getGarageByVille(String nomVille, int page, int perPage) {
        Ville ville = villeRepository.findByVille(nomVille);
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Garage> garages = garageRepository.findByVille(ville, pageable);
        long total = garages.getTotalElements();
        int totalPages = garages.getTotalPages();
        List<Garage> data = garages.getContent();
        return new GarageDto(page, perPage, total, totalPages, data);
    }

    public GarageDto getGarageByNomAndVille(String nom, String nomVille, int page, int perPage) {
        Ville ville = villeRepository.findByVille(nomVille);
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Garage> garages = garageRepository.findByNomAndVille(nom, ville, pageable);
        long total = garages.getTotalElements();
        int totalPages = garages.getTotalPages();
        List<Garage> data = garages.getContent();
        return new GarageDto(page, perPage, total, totalPages, data);
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