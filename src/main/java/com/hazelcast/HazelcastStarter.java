package com.hazelcast;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.File;

/**
 * Created by manishk on 10/13/16.
 */
public class HazelcastStarter {

    public static void main(String[] args) {
        Config config = new Config();

        // Create Join Config with TCP.
        config.getNetworkConfig().setJoin(new JoinConfig()
                .setMulticastConfig(
                        new MulticastConfig().setEnabled(false))
                .setTcpIpConfig(
                        new TcpIpConfig().setEnabled(true)));

        // Create SSL config.
        SSLConfig sslConfig = new SSLConfig();
        sslConfig.setEnabled(true);
        sslConfig.setFactoryClassName("com.hazelcast.nio.ssl.BasicSSLContextFactory");
        sslConfig.setProperty("keyStore", new File("hazelcast.ks").getAbsolutePath());
        sslConfig.setProperty("keyStorePassword", "password");
        sslConfig.setProperty("javax.net.ssl.trustStore", new File("hazelcast.ts").getAbsolutePath());
        config.setLicenseKey("ENTERPRISE_HD#10Nodes#y0KTwIaSH6r7mbO5EkUl1fJFuAjVN1290101600201911101010101191910"); // This is trial license.
        config.getNetworkConfig().setSSLConfig(sslConfig);
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        hazelcastInstance.getMap("someMap").put("name", "manish");
        System.out.println(hazelcastInstance.getMap("someMap").get("name"));
    }
}
