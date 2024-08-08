package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "statut_id")
    private StatutReservation statut;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "evenement_id")
    @JsonIgnoreProperties("reservation, utilisateur")
    private Evenement evenement;

    private Date date_res = new Date();

    @ManyToOne
    @JoinColumn(name = "methodePaiement_id")
    @JsonIgnoreProperties("reservation")
    private MethodePaiement methodePaiement;

   //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnoreProperties("reservation")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "categories")
    @JsonIgnoreProperties("reservation")
     private CategorieBillet category;
}


