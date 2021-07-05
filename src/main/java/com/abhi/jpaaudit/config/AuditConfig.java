//package com.abhi.jpaaudit.config;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//
//@Configuration
//@EnableJpaAuditing(auditorAwareRef = "auditProvider")
//public class AuditConfig {
//
//    @Bean
//    AuditorAware<String> auditProvider(){
//        return new AuditorAwareImpl();
//    }
//}
