package ru.evgeny.otus_spring3.quize.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.evgeny.otus_spring3.answer.Answer;
import ru.evgeny.otus_spring3.question.interfaces.IQuestion;
import ru.evgeny.otus_spring3.quize.intrfaces.IQuestionLoader;
import ru.evgeny.otus_spring3.quize.intrfaces.IQuize;

import java.util.List;

@Component
public class Quize implements IQuize {

    List<IQuestion> questions;

    @Autowired
    public Quize(IQuestionLoader loader) {
        questions = loader.load();
    }

    @Override
    public void start() {
        questions.forEach(IQuestion::ask);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void getResult() {
        int right = 0;
        for (IQuestion question: questions) {
            String questionName = question.getName();
            Answer<Boolean> answer = (Answer<Boolean>) question.getResult().get(question);
            if(answer.getPoint()) right++;
            System.out.println(questionName + "-" + answer.getName() + " " + answer.getPoint().toString());
        }
        System.out.println(right + "/" + questions.size());

    }
}
