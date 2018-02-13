/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.controllers;

import fr.service.management.exceptions.DoctorException;
import fr.service.management.models.Doctor;
import fr.service.management.models.Patient;
import fr.service.management.models.Secretaire;
import fr.service.management.services.DoctorService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zouhairhajji
 */
@RestController
@RequestMapping(value = "doctor")
public class DoctorController {

    private final static Logger logger = Logger.getLogger(DoctorController.class);
    
    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ResponseEntity<?> registerDoctor(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "doctorname", required = true) String doctorName) {
        try {
            Doctor doctor = this.doctorService.registerDoctor(username, doctorName);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (DoctorException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "list_secretaire", method = RequestMethod.GET)
    public ResponseEntity<?> getSecretaires(@RequestParam(value = "username", required = true) String username) {
        try {
            List<Secretaire> secretaires = this.doctorService.getSecretaires(username);
            return new ResponseEntity<>(secretaires, HttpStatus.OK);
        } catch (DoctorException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "list_patients", method = RequestMethod.GET)
    public ResponseEntity<?> getPatients(@RequestParam(value = "username", required = true) String username) {
        try {
            List<Patient> patients = this.doctorService.getPatients(username);
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (DoctorException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "add_secretaire", method = RequestMethod.GET)
    public ResponseEntity<?> addSecretaire(@RequestParam(value = "username", required = true) String username, 
            @RequestParam(value = "secretairename", required = true) String secretairename) {
        try {
            Secretaire secretaire = this.doctorService.addSecretaire(username, secretairename);
            return new ResponseEntity<>(secretaire, HttpStatus.OK);
        } catch (DoctorException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getDoctor(@RequestParam(value = "username", required = true) String username) {
        try {
            Doctor doctor = this.doctorService.getDoctor(username);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } catch (DoctorException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
}
