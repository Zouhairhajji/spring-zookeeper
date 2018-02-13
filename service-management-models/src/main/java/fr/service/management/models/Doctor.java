/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author zouhairhajji
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements Serializable{
    
    private String username;
    
    private String doctorName;
    
    private List<Secretaire> secretaires;
    
    
    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        if(secretaires == null){
            return patients;
        }
        
        for (Secretaire secretaire : secretaires) {
            patients.addAll(secretaire.getPatients());
        }
        
        return patients;
    }

    @Override
    public String toString() {
        return "Doctor{" + "username=" + username + ", doctorName=" + doctorName + ", secretaires=" + secretaires + '}';
    }
    
    
}
