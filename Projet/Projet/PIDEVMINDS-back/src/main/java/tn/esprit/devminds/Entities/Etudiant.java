package tn.esprit.devminds.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idEtudiant;
    int cin;
    String nom;
    String prenom;
    int numero;
    String email;
    @ManyToMany
    Set<Evenement> evenement;
    @OneToOne(mappedBy = "etudiant")
    DocStage docStage;
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.ALL)
    Set<Candidature> candidatures;
    @OneToMany (mappedBy = "etudiant",cascade = CascadeType.ALL)
    Set<messagerie> messageries;


}
