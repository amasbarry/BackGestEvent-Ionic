package com.kalanso.event.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String adresse;
    private String salle;
    private Integer capacite;

    @OneToMany(mappedBy = "lieu")
    private List<Derouler> derouler;
    // Getters, setters, constructeurs
}