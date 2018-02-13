/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.controllers;

import fr.service.management.exceptions.DoctorException;
import fr.service.management.exceptions.SecretaireException;
import fr.service.management.models.Patient;
import fr.service.management.models.Secretaire;
import fr.service.management.services.SecretaireService;
import java.util.List;
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
@RequestMapping(value = "secretaire")
public class SecretaireController {

    @Autowired
    private SecretaireService secretaireService;

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SecretaireController.class);

    @RequestMapping(value = "patients", method = RequestMethod.GET)
    public ResponseEntity<?> getPatients(
            @RequestParam(value = "doctorusername", required = true) String doctorusername,
            @RequestParam(value = "secretaireName", required = true) String secretaireName) {

        try {
            List<Patient> patients = this.secretaireService.getPatients(doctorusername, secretaireName);
            return new ResponseEntity<>(patients, HttpStatus.OK);
        } catch (DoctorException | SecretaireException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getSecretaire(
            @RequestParam(value = "doctorusername", required = true) String doctorusername,
            @RequestParam(value = "secretaireName", required = true) String secretaireName) {

        try {
            Secretaire secretaire = this.secretaireService.getSecretaire(doctorusername, secretaireName);
            return new ResponseEntity<>(secretaire, HttpStatus.OK);
        } catch (DoctorException | SecretaireException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "add_patient", method = RequestMethod.GET)
    public ResponseEntity<?> addPatient(
            @RequestParam(value = "doctorusername", required = true) String doctorusername,
            @RequestParam(value = "secretaireName", required = true) String secretaireName,
            @RequestParam(value = "patientname", required = true) String patientname,
            @RequestParam(value = "patientdescription", required = true) String patientdescription) {

        try {
            Patient patient = this.secretaireService.addPatient(doctorusername, secretaireName, patientname, patientdescription);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (DoctorException | SecretaireException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "delete_patient", method = RequestMethod.GET)
    public ResponseEntity<?> deletePatient(
            @RequestParam(value = "doctorusername", required = true) String doctorusername,
            @RequestParam(value = "secretaireName", required = true) String secretaireName,
            @RequestParam(value = "patientname", required = true) String patientname) {

        try {
            Boolean removed = this.secretaireService.deletePatient(doctorusername, secretaireName, patientname);
            return new ResponseEntity<>(removed, HttpStatus.OK);
        } catch (DoctorException | SecretaireException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
    
    @RequestMapping(value = "update_patient", method = RequestMethod.GET)
    public ResponseEntity<?> updatePatient(
            @RequestParam(value = "doctorusername", required = true) String doctorusername,
            @RequestParam(value = "secretaireName", required = true) String secretaireName,
            @RequestParam(value = "patientname", required = true) String patientname,
            @RequestParam(value = "patientdescription", required = true) String patientdescription) {

        try {
            Patient patient = this.secretaireService.updatePatient(doctorusername, secretaireName, patientname, patientdescription);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (DoctorException | SecretaireException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return new ResponseEntity<>("Une erreure est survenue lors du traitement", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
