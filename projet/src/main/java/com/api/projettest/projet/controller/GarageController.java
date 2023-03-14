package com.api.projettest.projet.controller;

import com.api.projettest.projet.model.Garage;
import com.api.projettest.projet.model.GarageDto;
import com.api.projettest.projet.service.GarageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GarageController {

    private final GarageService garageService;

    @GetMapping("/garages")
    public GarageDto read(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int perPage, @RequestParam(required = false, defaultValue = "") String nom, @RequestParam(required = false, defaultValue = "") String ville) {
        if(nom.equals("") && ville.equals("")){
            return garageService.getAllGarages(page, perPage);
        }
        if(ville.equals("")){
            return garageService.getGarageByNom(nom, page, perPage);
        }
        if(nom.equals("")){
            return garageService.getGarageByVille(ville, page, perPage);
        }
        return garageService.getGarageByNomAndVille(nom, ville, page, perPage);
    }

    @GetMapping("/garages/{id_garage}")
    public Optional<Garage> getGarageById(@PathVariable Long id_garage){
        return garageService.getGarageById(id_garage);
    }

    @PostMapping("/garages")
    public Garage create(@RequestBody Garage garage) {
        return garageService.addGarage(garage);
    }

    @PutMapping("/garages/{id_garage}")
    public Garage update(@PathVariable Long id_garage, @RequestBody Garage garage){
        return garageService.updateGarage(id_garage, garage);
    }

    @DeleteMapping("/garages/{id_garage}")
    public String delete(@PathVariable Long id_garage){
        return garageService.deleteGarage(id_garage);
    }
}