package ru.evgeny.otus_spring4.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadDbSettings {
    private String dbUrl;
    private String dbDriver;
    private String dbUser;

    @Autowired
    public LoadDbSettings(ApplicationConfiguration conf) {
        dbUrl = conf.getDbUrl();
        dbDriver = conf.getDbDriver();
        dbUser = conf.getDbUser();
    }


    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbUser() {
        return dbUser;
    }
}
