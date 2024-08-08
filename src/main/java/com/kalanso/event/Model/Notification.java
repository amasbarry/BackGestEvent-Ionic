package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    @JsonIgnoreProperties("evenement")
    private Evenement evenement;

    private String sujet;
    private Date dateEnvoi = new Date();

    @ManyToOne
    @JoinColumn(name = "statutEnvoi_id")
    private StatutEnvoi status;

    private String message;
    private String dest_email;
}
