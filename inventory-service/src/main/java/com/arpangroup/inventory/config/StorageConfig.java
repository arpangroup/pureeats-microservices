package com.arpangroup.inventory.config;

import com.arpangroup.inventory.registry.StorageRegistry;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Bean
    public FactoryBean<?> factoryBean() {
        final ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
        bean.setServiceLocatorInterface(StorageRegistry.class);
        return bean;
    }
}
