package fr.service.management.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zouhairhajji
 */
@Component
public class GatewayZookeeper {

    @Value("${zookeeper.hosts}")
    private String hosts;

    private final static Logger logger = Logger.getLogger(GatewayZookeeper.class);

    private List<CuratorFramework> curatorFrameworks;

    @PostConstruct
    public void postConstruct() {
        this.curatorFrameworks = new ArrayList<>();

        
        for (StringTokenizer stringTokenizer = new StringTokenizer(hosts); stringTokenizer.hasMoreTokens();) {
            String host = stringTokenizer.nextToken();
            
            CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                    .connectString(host)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .namespace(UUID.randomUUID().toString())
                    .build();

            curatorFramework.start();
            this.curatorFrameworks.add(curatorFramework);
        }
        
        
    }

    public Optional<CuratorFramework> getRandomCuratorFramework() {
        Optional<CuratorFramework> anyCuratorFramework = this.curatorFrameworks
                .stream()
                .filter(cf -> cf.getZookeeperClient().isConnected())
                .findAny();
        return anyCuratorFramework;
    }
    
    
    @Scheduled(fixedDelay = 1000 * 10)
    public void printCheckedState(){
        for (CuratorFramework curatorFramework : curatorFrameworks) {
            System.out.println("-> " + curatorFramework.getNamespace());
            System.out.println("   state :> " + curatorFramework.getZookeeperClient().isConnected());
        }
    }
    
    @Scheduled(fixedDelay = 1000 )
    public void getStartedInstance(){
        Optional<CuratorFramework> optional = this.getRandomCuratorFramework();
        System.out.println("Started Instance is : " + optional.get());
    }

}
