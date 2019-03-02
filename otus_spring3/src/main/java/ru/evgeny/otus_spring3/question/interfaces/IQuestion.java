package ru.evgeny.otus_spring3.question.interfaces;

import ru.evgeny.otus_spring3.answer.Answer;

import java.util.Map;

public interface IQuestion<T> {

    void print();
    void save(IQuestion question);
    void ask();
    Map<IQuestion<T>, Answer<T>> getResult();
    String getName();

}
