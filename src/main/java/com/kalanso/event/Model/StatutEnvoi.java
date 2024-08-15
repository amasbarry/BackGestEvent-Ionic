package com.kalanso.event.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class StatutEnvoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long Id;
    private String statut;
    @JsonIgnore
    @OneToMany(mappedBy = "status")
    private List<Notification> notification;

}