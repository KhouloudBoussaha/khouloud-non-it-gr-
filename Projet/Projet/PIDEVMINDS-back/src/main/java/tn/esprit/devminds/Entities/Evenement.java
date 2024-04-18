package tn.esprit.devminds.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idEvenement;
    String titre;
    String description;
    @Temporal(TemporalType.DATE)
    Date dateEvt;
    String salle;
    @JsonIgnore
    @ManyToOne
    Administration administration;
    @ManyToMany(mappedBy = "evenement",cascade = CascadeType.ALL)
    Set<Etudiant> etudiants;



}
