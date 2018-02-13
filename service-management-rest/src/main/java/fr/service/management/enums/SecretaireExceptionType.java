/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.enums;

/**
 *
 * @author zouhairhajji
 */
public enum SecretaireExceptionType {

    NO_DOCTOR_FOUND("Aucun docteur n'est trouvé"),
    DOCTOR_ALREADY_EXIST("Le docteur existe déjà dans l'annuaire"),
    CANNOT_ACCES_ZK("La connexion est refusé à la base de données"),
    
    PATIENT_DOESNT_EXIST("Le patient n'existe pas  dans l'annuaire"),
    PATIENT_ALREADY_EXIST("Le patient existe déjà dans l'annuaire"),
    NO_SECRETAIRE_FOUND("Aucune secretaire n'est attribuée au docteur"),
    SECRETAIRE_ALREADY_EXISTS("La secretaire est déjà attribuée au docteur");
    

    private final String description;

    SecretaireExceptionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
