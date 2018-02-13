/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.service.management.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 *
 * @author zouhairhajji
 */
public class ZkHelper {

    public static void znode_create(ZooKeeper zooKeeper, String path, byte[] data) throws KeeperException, InterruptedException {
        zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    // Method to check existence of znode and its status, if znode is available.
    public static Stat znode_exists_stat(ZooKeeper zooKeeper, String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, true);
    }

    public static Boolean znode_exists(ZooKeeper zooKeeper, String path) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, true) != null;
    }

    // Method to update the data in a znode. Similar to getData but without watcher.
    public static void znode_update(ZooKeeper zooKeeper, String path, byte[] data) throws KeeperException, InterruptedException {
        zooKeeper.setData(path, data, zooKeeper.exists(path, true).getVersion());
    }

    public static byte[] znode_getData(ZooKeeper zooKeeper, String path) throws KeeperException, InterruptedException {
        byte[] bn = zooKeeper.getData(path, false, null);
        return bn;
    }

    public static List<String> znode_childrens(ZooKeeper zooKeeper, String path) throws KeeperException, InterruptedException {
        List<String> childrens = zooKeeper.getChildren(path, false);
        return childrens;
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

}
