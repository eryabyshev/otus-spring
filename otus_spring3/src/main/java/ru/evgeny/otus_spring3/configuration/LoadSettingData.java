package ru.evgeny.otus_spring3.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadSettingData {
    private String csvPath;

    @Autowired
    LoadSettingData(ApplicationSettings settings) {
        csvPath = settings.getCsvPath();
    }

    public String getCsvPath() {
        return csvPath;
    }
}
