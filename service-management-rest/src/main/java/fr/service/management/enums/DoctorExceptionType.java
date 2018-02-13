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
public enum DoctorExceptionType {

    NO_DOCTOR_FOUND("Aucun docteur n'est trouvé"),
    DOCTOR_ALREADY_EXIST("Le docteur existe déjà dans l'annuaire"),
    CANNOT_ACCES_ZK("La connexion est refusé à la base de données");

    private final String description;

    DoctorExceptionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
