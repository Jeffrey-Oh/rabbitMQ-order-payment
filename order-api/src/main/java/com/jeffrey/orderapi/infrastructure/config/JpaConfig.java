package com.jeffrey.orderapi.infrastructure.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
    basePackages = "com.jeffrey"
)
@RequiredArgsConstructor
public class JpaConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            log.info("Initialize JDBC connection.");
        } catch (Exception e) {
            log.error("Failed to initialize JDBC connection.");
        }
    }

}
