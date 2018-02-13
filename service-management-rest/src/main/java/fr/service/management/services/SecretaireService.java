/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.services;

import fr.service.management.components.GatewayZookeeper;
import fr.service.management.enums.DoctorExceptionType;
import static fr.service.management.enums.DoctorExceptionType.CANNOT_ACCES_ZK;
import fr.service.management.enums.SecretaireExceptionType;
import fr.service.management.exceptions.DoctorException;
import fr.service.management.exceptions.SecretaireException;
import fr.service.management.helpers.ZkHelper;
import fr.service.management.models.Doctor;
import fr.service.management.models.Patient;
import fr.service.management.models.Secretaire;
import java.util.List;
import java.util.Optional;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zouhairhajji
 */
@Service("secretaireService")
public class SecretaireService {

    @Autowired
    private GatewayZookeeper gatewayZookeeper;

    public List<Patient> getPatients(String doctorusername, String secretaireName) throws DoctorException, Exception, SecretaireException {
        Secretaire secretaire = getSecretaire(doctorusername, secretaireName);
        return secretaire.getPatients();
    }

    public Secretaire getSecretaire(String doctorusername, String secretaireName) throws DoctorException, Exception, SecretaireException {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + doctorusername;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] docteur = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(docteur);
            Optional<Secretaire> optDoctor = doctor.getSecretaires()
                    .stream()
                    .filter(s -> s.getName().trim().equalsIgnoreCase(secretaireName))
                    .findFirst();

            if (!optDoctor.isPresent()) {
                throw new SecretaireException(SecretaireExceptionType.NO_SECRETAIRE_FOUND);
            }

            return optDoctor.get();
        }
    }

    public Patient addPatient(String doctorusername, String secretaireName, String patientname, String patientdescription) throws DoctorException, Exception, SecretaireException {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + doctorusername;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] docteur = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(docteur);
            Optional<Secretaire> optDoctor = doctor.getSecretaires()
                    .stream()
                    .filter(s -> s.getName().trim().equalsIgnoreCase(secretaireName))
                    .findFirst();

            if (!optDoctor.isPresent()) {
                throw new SecretaireException(SecretaireExceptionType.NO_SECRETAIRE_FOUND);
            }
            Optional<Patient> optPatient = optDoctor.get().getPatients()
                    .stream()
                    .filter(p -> p.getFullName().trim().equalsIgnoreCase(patientname))
                    .findAny();
            if(optPatient.isPresent()){
                throw new SecretaireException(SecretaireExceptionType.PATIENT_ALREADY_EXIST);
            }
            
            Patient patient = Patient.builder()
                    .description(patientdescription)
                    .fullName(patientname)
                    .build();
            optDoctor.get().getPatients().add(patient);
            ZkHelper.znode_update(zooKeeper, path, ZkHelper.serialize(doctor));
            return patient;
        }
    }

    public Patient updatePatient(String doctorusername, String secretaireName, String patientname, String patientdescription) throws DoctorException, Exception, SecretaireException {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + doctorusername;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] docteur = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(docteur);
            Optional<Secretaire> optDoctor = doctor.getSecretaires()
                    .stream()
                    .filter(s -> s.getName().trim().equalsIgnoreCase(secretaireName))
                    .findFirst();

            if (!optDoctor.isPresent()) {
                throw new SecretaireException(SecretaireExceptionType.NO_SECRETAIRE_FOUND);
            }
            Optional<Patient> optPatient = optDoctor.get().getPatients()
                    .stream()
                    .filter(p -> p.getFullName().trim().equalsIgnoreCase(patientname))
                    .findAny();
            if( ! optPatient.isPresent()){
                throw new SecretaireException(SecretaireExceptionType.PATIENT_DOESNT_EXIST);
            }
            
            optPatient.get().setDescription(patientdescription);
            
            
            ZkHelper.znode_update(zooKeeper, path, ZkHelper.serialize(doctor));
            return optPatient.get();
        }
    }

    
    public Boolean deletePatient(String doctorusername, String secretaireName, String patientname)  throws DoctorException, Exception, SecretaireException{
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + doctorusername;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] docteur = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(docteur);
            Optional<Secretaire> optSecretaire = doctor.getSecretaires()
                    .stream()
                    .filter(s -> s.getName().trim().equalsIgnoreCase(secretaireName))
                    .findFirst();

            if (!optSecretaire.isPresent()) {
                throw new SecretaireException(SecretaireExceptionType.NO_SECRETAIRE_FOUND);
            }
            Optional<Patient> optPatient = optSecretaire.get().getPatients()
                    .stream()
                    .filter(p -> p.getFullName().trim().equalsIgnoreCase(patientname))
                    .findAny();
            if( ! optPatient.isPresent()){
                throw new SecretaireException(SecretaireExceptionType.PATIENT_DOESNT_EXIST);
            }
            

            boolean removed = optSecretaire.get().getPatients().remove(optPatient.get());
            ZkHelper.znode_update(zooKeeper, path, ZkHelper.serialize(doctor));
            return removed;
        }
    }

    

}
