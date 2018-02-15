/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author zouhairhajji
 */
@Controller
@RequestMapping("doctor")
public class DoctorController {
    
    
    @RequestMapping(value="auth", method = RequestMethod.GET)
    public String doctorAuthentification(Model model){
        
        return  "/WEB-INF/jsp/doctor/auth.jsp";
    }
    
    @RequestMapping(value="register", method = RequestMethod.GET)
    public String doctorregistration(Model model){
        
        return  "/WEB-INF/jsp/doctor/register.jsp";
    }
    
    @RequestMapping(value="list_secretaires", method = RequestMethod.GET)
    public String findSecretaires(Model model){
        
        return  "/WEB-INF/jsp/doctor/list_secretaires.jsp";
    }
    
    @RequestMapping(value="add_secretaire", method = RequestMethod.GET)
    public String addSecretaire(Model model){
        
        return  "/WEB-INF/jsp/doctor/add_secretaire.jsp";
    }
    
    @RequestMapping(value="list_patients", method = RequestMethod.GET)
    public String findPatients(Model model){
        
        return  "/WEB-INF/jsp/doctor/list_patients.jsp";
    }
    
}
