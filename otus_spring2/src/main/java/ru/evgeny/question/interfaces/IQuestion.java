package ru.evgeny.question.interfaces;

import ru.evgeny.answer.Answer;

import java.util.Map;

public interface IQuestion<T> {

    void print();
    void save(IQuestion question);
    void ask();
    Map<IQuestion<T>, Answer<T>> getResult();
    String getName();

}
