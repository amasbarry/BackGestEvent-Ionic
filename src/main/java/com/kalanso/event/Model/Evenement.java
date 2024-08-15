package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private Date date = new Date();
    private Date datedebut ;
    private Date datefin ;
    private String description;
    private Integer nombrePlace;

    @ManyToOne
    @JoinColumn(name = "typeEvent_id")
    @JsonIgnoreProperties("evenement")
    private TypeEvent typeevent;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonIgnoreProperties({"evenement", "reservation", "notification", "presta"})
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "categories")
    @JsonIgnoreProperties("evenement")
    private CategorieEvent category;

    @OneToMany(mappedBy = "evenement")
    @JsonIgnoreProperties({"utilisateur", "evenement"})
    private List<Notification> notification;

    @JsonIgnore
    @OneToMany(mappedBy = "evenement")
    private List<Derouler> derouler;

    @OneToMany(mappedBy = "evenement")
    @JsonIgnoreProperties({"utilisateur", "evenement"})
    private List<Reservation> reservation;

    // Getters, setters, constructeurs
}