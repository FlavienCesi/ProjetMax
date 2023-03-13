package com.api.projettest.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.projettest.projet.model.*;
import com.api.projettest.projet.repository.*;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoitureService {
    @Autowired
    private final VoitureRepository voitureRepository;
    private final MarqueRepository marqueRepository;
    private final ModeleRepository modeleRepository;
    private final AnneeRepository anneeRepository;
    //private final GarageRepository garageRepository;

    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
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
        voiture.setMarque(marque);
        voiture.setModele(modele);
        voiture.setAnnee(annee);
        return voitureRepository.save(voiture);
    }
    

    public Voiture updateVoiture(Long id_voiture, Voiture voiture) {
        return voitureRepository.findById(id_voiture)
                .map(p-> {
                    p.setPlaque(voiture.getPlaque());
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
                    p.setMarque(marque);
                    p.setModele(modele);
                    p.setAnnee(annee);
    
                    return voitureRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Voiture non trouvé !"));
    }
    

    public String deleteVoiture(Long id_voiture) {
        voitureRepository.deleteById(id_voiture);
        return "Voiture supprimé";
    }
}
