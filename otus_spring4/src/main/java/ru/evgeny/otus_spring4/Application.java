package ru.evgeny.otus_spring4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.evgeny.otus_spring4.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.evgeny.otus_spring4.configuration.LoadDbSettings;

import javax.sql.DataSource;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
public class Application {

    @Bean
    @Autowired
    public JdbcOperations jdbcOperations(LoadDbSettings settings) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(settings.getDbUrl());
        ds.setDriverClassName(settings.getDbDriver());
        ds.setUsername(settings.getDbUser());
        return new JdbcTemplate(ds);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
