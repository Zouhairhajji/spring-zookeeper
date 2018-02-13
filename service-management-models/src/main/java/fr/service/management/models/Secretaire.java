/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.models;

import java.io.Serializable;
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
public class Secretaire implements Serializable{
    
    private String name;
    
    
    private List<Patient> patients;

    @Override
    public String toString() {
        return "Secretaire{" + "name=" + name + ", patients=" + patients + '}';
    }
    
    
}
