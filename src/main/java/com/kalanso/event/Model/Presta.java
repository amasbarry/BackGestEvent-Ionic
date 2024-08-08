package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "presta")
public class Presta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_presta;
    private String email;
    private Integer tel;
    private String profile;

    @OneToMany(mappedBy = "presta")
    @JsonIgnoreProperties("presta")
    private List<Equipement> equipement;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnoreProperties({"presta", "evenement", "reservation", "notification"})
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "rolePrestateur_id")
    @JsonIgnoreProperties("prestateur")
    private RolePrestateur rolePrestateur;
}
