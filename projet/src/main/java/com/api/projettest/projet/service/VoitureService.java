package com.api.projettest.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.projettest.projet.model.*;
import com.api.projettest.projet.repository.*;
import com.api.projettest.projet.specification.*;

import com.api.projettest.projet.model.VoitureDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoitureService {
    @Autowired
    private final VoitureRepository voitureRepository;
    private final MarqueRepository marqueRepository;
    private final ModeleRepository modeleRepository;
    private final AnneeRepository anneeRepository;
    private final CarburantRepository carburantRepository;

    public VoitureDto getVoituresByCriteria(
        Integer kilometrageMin,
        Integer kilometrageMax,
        String marque,
        String modele,
        Integer anneeMin,
        Integer anneeMax,
        List<String> carburants,
        Boolean garage,
        Integer page,
        Integer perPage) {
        Specification<Voiture> spec = Specification.where(VoitureSpecifications.filterByMarque(marque))
        .and(VoitureSpecifications.filterByKilometreBetween(kilometrageMin, kilometrageMax))
        .and(VoitureSpecifications.filterByModele(modele))
        .and(VoitureSpecifications.filterByAnneeBetween(anneeMin, anneeMax))
        .and(VoitureSpecifications.filterByCarburant(carburants))
        .and(VoitureSpecifications.filterByGarage(garage));

        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Voiture> voitures = voitureRepository.findAll(spec, pageable);
        long total = voitures.getTotalElements();
        int totalPages = voitures.getTotalPages();
        List<Voiture> data = voitures.getContent();
        return new VoitureDto(page, perPage, total, totalPages, data);
    }

    public Optional<Voiture> getVoitureById(Long id_voiture) {
        return voitureRepository.findById(id_voiture);
    }

    public Voiture addVoiture(Voiture voiture) {
        Marque marque = marqueRepository.findByMarque(voiture.getMarque().getMarque());
        if (marque == null) {
            marque = new Marque();
            marque.setMarque(voiture.getMarque().getMarque());
            marque = marqueRepository.save(marque);
        }
        Modele modele = modeleRepository.findByModele(voiture.getModele().getModele());
        if (modele == null) {
            modele = new Modele();
            modele.setModele(voiture.getModele().getModele());
            modele = modeleRepository.save(modele);
        }
        Annee annee = anneeRepository.findByAnnee(voiture.getAnnee().getAnnee());
        if (annee == null) {
            annee = new Annee();
            annee.setAnnee(voiture.getAnnee().getAnnee());
            annee = anneeRepository.save(annee);
        }
        Carburant carburant = carburantRepository.findByCarburant(voiture.getCarburant().getCarburant());
        if (carburant == null) {
            carburant = new Carburant();
            carburant.setCarburant(voiture.getCarburant().getCarburant());
            carburant = carburantRepository.save(carburant);
        }
        voiture.setMarque(marque);
        voiture.setModele(modele);
        voiture.setAnnee(annee);
        voiture.setCarburant(carburant);
        return voitureRepository.save(voiture);
    }
    

    public Voiture updateVoiture(Long id_voiture, Voiture voiture) {
        return voitureRepository.findById(id_voiture)
                .map(p-> {
                    p.setPlaque(voiture.getPlaque());
                    p.setKilometre(voiture.getKilometre());
                    Marque marque = marqueRepository.findByMarque(voiture.getMarque().getMarque());
                    if (marque == null) {
                        marque = new Marque();
                        marque.setMarque(voiture.getMarque().getMarque());
                        marque = marqueRepository.save(marque);
                    }
                    Modele modele = modeleRepository.findByModele(voiture.getModele().getModele());
                    if (modele == null) {
                        modele = new Modele();
                        modele.setModele(voiture.getModele().getModele());
                        modele = modeleRepository.save(modele);
                    }
                    Annee annee = anneeRepository.findByAnnee(voiture.getAnnee().getAnnee());
                    if (annee == null) {
                        annee = new Annee();
                        annee.setAnnee(voiture.getAnnee().getAnnee());
                        annee = anneeRepository.save(annee);
                    }
                    Carburant carburant = carburantRepository.findByCarburant(voiture.getCarburant().getCarburant());
                    if (carburant == null) {
                        carburant = new Carburant();
                        carburant.setCarburant(voiture.getCarburant().getCarburant());
                        carburant = carburantRepository.save(carburant);
                    }
                    p.setMarque(marque);
                    p.setModele(modele);
                    p.setAnnee(annee);
                    p.setCarburant(carburant);
                    p.setGarage(voiture.getGarage());
    
                    return voitureRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Voiture non trouvé !"));
    }
    

    public String deleteVoiture(Long id_voiture) {
        voitureRepository.deleteById(id_voiture);
        return "Voiture supprimé";
    }
}
