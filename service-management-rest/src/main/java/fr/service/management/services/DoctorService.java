/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.services;

import fr.service.management.components.GatewayZookeeper;
import fr.service.management.enums.DoctorExceptionType;
import static fr.service.management.enums.DoctorExceptionType.CANNOT_ACCES_ZK;
import fr.service.management.exceptions.DoctorException;
import fr.service.management.helpers.ZkHelper;
import fr.service.management.models.Doctor;
import fr.service.management.models.Patient;
import fr.service.management.models.Secretaire;
import java.util.ArrayList;
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
@Service
public class DoctorService {

    @Autowired
    private GatewayZookeeper gatewayZookeeper;

    public Doctor registerDoctor(String username, String doctorName) throws DoctorException, Exception {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + username;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            Doctor doctor = Doctor.builder()
                    .username(username)
                    .doctorName(doctorName)
                    .secretaires(new ArrayList<>())
                    .build();
            ZkHelper.znode_create(zooKeeper, path, ZkHelper.serialize(doctor));
            return doctor;
        } else {
            throw new DoctorException(DoctorExceptionType.DOCTOR_ALREADY_EXIST);
        }
    }

    public List<Secretaire> getSecretaires(String username) throws DoctorException, Exception {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + username;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] docteur = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(docteur);
            return doctor.getSecretaires();
        }
    }

    public List<Patient> getPatients(String username) throws DoctorException, Exception {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + username;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] bytes = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(bytes);
            
            
            return doctor.getAllPatients();
        }
    }

    public Secretaire addSecretaire(String username, String secretairename) throws DoctorException, Exception {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + username;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] docteur = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(docteur);
            Secretaire secretaire = Secretaire.builder()
                    .name(secretairename)
                    .patients(new ArrayList<>())
                    .build();
            doctor.getSecretaires().add(secretaire);
            ZkHelper.znode_update(zooKeeper, path, ZkHelper.serialize(doctor));
            return secretaire;
        }
    }

    public Doctor getDoctor(String username) throws DoctorException, Exception {
        Optional<CuratorFramework> optional = this.gatewayZookeeper.getRandomCuratorFramework();
        if (!optional.isPresent()) {
            throw new DoctorException(CANNOT_ACCES_ZK);
        }
        CuratorFramework curatorFramework = optional.get();
        ZooKeeper zooKeeper = curatorFramework.getZookeeperClient().getZooKeeper();

        String path = "/doctoroffice/doctors/" + username;

        if (!ZkHelper.znode_exists(zooKeeper, path)) {
            throw new DoctorException(DoctorExceptionType.NO_DOCTOR_FOUND);
        } else {
            byte[] docteur = ZkHelper.znode_getData(zooKeeper, path);
            Doctor doctor = (Doctor) ZkHelper.deserialize(docteur);
            
            return doctor;
        }
    }

}
