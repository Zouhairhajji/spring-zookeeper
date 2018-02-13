/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.models;

import java.io.Serializable;
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
public class Patient implements Serializable{
   
    private String fullName;
    
    private String description;

    @Override
    public String toString() {
        return "Patient{" + "fullName=" + fullName + ", description=" + description + '}';
    }
    
    
    
}
