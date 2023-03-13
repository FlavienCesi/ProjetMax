package com.api.projettest.projet.controller;

import com.api.projettest.projet.model.Voiture;
import com.api.projettest.projet.service.VoitureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class VoitureController {
    private final VoitureService voitureService;

    @GetMapping("/voitures")
    public List<Voiture> read() {
        return voitureService.getAllVoitures();
    }

    @GetMapping("/voitures/{id_voiture}")
    public Optional<Voiture> getVoitureById(@PathVariable Long id_voiture){
        return voitureService.getVoitureById(id_voiture);
    }

    @PostMapping("/voitures")
    public Voiture create(@RequestBody Voiture voiture) {
        return voitureService.addVoiture(voiture);
    }

    @PutMapping("/voitures/{id_voiture}")
    public Voiture update(@PathVariable Long id_voiture, @RequestBody Voiture voiture){
        return voitureService.updateVoiture(id_voiture, voiture);
    }

    @DeleteMapping("/voitures/{id_voiture}")
    public String delete(@PathVariable Long id_voiture){
        return voitureService.deleteVoiture(id_voiture);
    }
}
