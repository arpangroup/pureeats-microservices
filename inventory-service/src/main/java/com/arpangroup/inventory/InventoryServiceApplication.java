package com.arpangroup.inventory;

import com.arpangroup.inventory.config.StorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.net.InetAddress;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class InventoryServiceApplication implements CommandLineRunner {

    @Autowired
    private ServerProperties serverProperties;

//    @Value("${validation.constraints.phone.message}")
//    private String errorMessage;

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n##############################################");
        System.out.println("Application Started on " + InetAddress.getLocalHost().getHostAddress() + ":" +serverProperties.getPort());
        System.out.println("##############################################");
    }
}
