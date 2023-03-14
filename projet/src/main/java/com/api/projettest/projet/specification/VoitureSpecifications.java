package com.api.projettest.projet.specification;

import com.api.projettest.projet.model.Voiture;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import javax.persistence.criteria.Predicate;

public class VoitureSpecifications {

    public static Specification<Voiture> filterByKilometreBetween(Integer kilometre1, Integer kilometre2) {
        return (root, query, criteriaBuilder) -> {
            if (kilometre1 == null && kilometre2 == null) {
                return criteriaBuilder.conjunction();
            } else if (kilometre1 != null && kilometre2 == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("kilometre"), kilometre1);
            } else if (kilometre1 == null && kilometre2 != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("kilometre"), kilometre2);
            } else {
                return criteriaBuilder.between(root.get("kilometre"), kilometre1, kilometre2);
            }
        };
    }

    public static Specification<Voiture> filterByMarque(String nom_marque) {
        return (root, query, criteriaBuilder) -> {
            if (nom_marque == null) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.equal(root.get("marque").get("marque"), nom_marque);
            }
        };
    }

    public static Specification<Voiture> filterByModele(String nom_modele) {
        return (root, query, criteriaBuilder) -> {
            if (nom_modele == null) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.equal(root.get("modele").get("modele"), nom_modele);
            }
        };
    }

    public static Specification<Voiture> filterByAnneeBetween(Integer annee1, Integer annee2) {
        return (root, query, criteriaBuilder) -> {
            if (annee1 == null && annee2 == null) {
                return criteriaBuilder.conjunction();
            } else if (annee1 != null && annee2 == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("annee").get("annee"), annee1);
            } else if (annee1 == null && annee2 != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("annee").get("annee"), annee2);
            } else {
                return criteriaBuilder.between(root.get("annee").get("annee"), annee1, annee2);
            }
        };
    }
    public static Specification<Voiture> filterByCarburant(List<String> nom_carburants) {
        return (root, query, criteriaBuilder) -> {
            if (nom_carburants == null || nom_carburants.isEmpty()) {
                return criteriaBuilder.conjunction();
            } else {
                Predicate[] predicates = nom_carburants.stream()
                        .map(nom_carburant -> criteriaBuilder.equal(root.get("carburant").get("carburant"), nom_carburant))
                        .toArray(Predicate[]::new);
                return criteriaBuilder.or(predicates);
            }
        };
    }
    public static Specification<Voiture> filterByGarage(Boolean garage) {
        return (root, query, criteriaBuilder) -> {
            if (garage == null) {
            return criteriaBuilder.conjunction();
            } else if (garage) {
            return criteriaBuilder.isNotNull(root.get("garage"));
            } else {
            return criteriaBuilder.isNull(root.get("garage"));
            }
        };
    }
}