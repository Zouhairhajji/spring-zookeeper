package fr.service.management.exceptions;

import fr.service.management.enums.DoctorExceptionType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zouhairhajji
 */
public class DoctorException extends Throwable{

    public DoctorException(DoctorExceptionType doctorExceptionMessage){
        super(doctorExceptionMessage.getDescription());
    }
    
    public DoctorException(String message){
        super(message);
    }
    
    
    
}
