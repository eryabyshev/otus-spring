package ru.evgeny.otus_spring3.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("csv")
public class ApplicationSettings {
    private String path;

    public String getCsvPath() {
        return path;
    }

    public void setCsvPath(String path) {
        this.path = path;
    }
}
