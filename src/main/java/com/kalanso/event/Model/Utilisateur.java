package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Getter
@Setter

public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telephone;
    private String name;
    private String type;
    @Lob
    @Column(name = "imagedata",length = 1000, unique = true)
    private byte[] imageData;
    //@Column(unique = true)
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleUser role;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties("utilisateur")
    private List<Evenement> evenement;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties("utilisateur")
    private List<Reservation> reservation;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties("utilisateur")
    private List<Notification> notification;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnoreProperties("utilisateur")
    private List<Presta> presta;
}
