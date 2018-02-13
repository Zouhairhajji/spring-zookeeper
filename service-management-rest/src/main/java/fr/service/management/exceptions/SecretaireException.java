/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.exceptions;

import fr.service.management.enums.SecretaireExceptionType;

/**
 *
 * @author zouhairhajji
 */
public class SecretaireException extends Throwable{

    public SecretaireException(String message) {
        super(message);
    }
    
    public SecretaireException(SecretaireExceptionType secretaireExceptionType) {
        super(secretaireExceptionType.getDescription());
    }
}
