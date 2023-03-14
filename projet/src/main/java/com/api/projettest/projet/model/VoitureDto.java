package com.api.projettest.projet.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoitureDto {
    private int page;
    private int per_page;
    private long total;
    private int total_pages;
    private List<Voiture> data;
}
