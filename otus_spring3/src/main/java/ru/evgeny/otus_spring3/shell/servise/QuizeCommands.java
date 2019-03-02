package ru.evgeny.otus_spring3.shell.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.evgeny.otus_spring3.shell.servise.interfaces.IApllicationStart;

@ShellComponent
public class QuizeCommands {
    private IApllicationStart apllicationStart;

    @Autowired
    public QuizeCommands(IApllicationStart apllicationStart) {
        this.apllicationStart = apllicationStart;
    }

    @ShellMethod("Start quize")
    public void start(@ShellOption String firstname, String lastName) {
        apllicationStart.setName(firstname, lastName);
    }


}
