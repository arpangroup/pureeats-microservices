package com.arpangroup.inventory.config;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    private final EntityManagerFactory entityManagerFactory;

    AuditConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }

    @Bean
    AuditReader auditReader() {
        return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
    }
}
