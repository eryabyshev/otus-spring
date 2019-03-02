package ru.evgeny.otus_spring3.shell.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.evgeny.otus_spring3.quize.intrfaces.IQuize;
import ru.evgeny.otus_spring3.shell.servise.interfaces.IApllicationStart;

@Service
public class QuizeApplicationStart implements IApllicationStart {

    private IQuize quize;

    @Autowired
    public QuizeApplicationStart(IQuize quize) {
        this.quize = quize;
    }

    @Override
    public void start() {
        System.out.println("Welcome to our quiz");
    }

    @Override
    public void setName(String first, String last) {
        quize.start();
        System.out.println("Result of quize for " + first + " " + last);
        quize.getResult();
    }
}
