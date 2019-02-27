package ru.evgeny.question.impl;

import org.springframework.stereotype.Service;
import ru.evgeny.answer.Answer;
import ru.evgeny.question.interfaces.IQuestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class CsvQuestion implements IQuestion<Boolean> {
    private String question;
    private List<Answer<Boolean>> answerList;
    Map<IQuestion<Boolean>, Answer<Boolean>> result = new HashMap<>();


    public CsvQuestion(String name, List<Answer<Boolean>> answer) {
        this.question = name;
        this.answerList = answer;
    }


    @Override
    public void print() {
        System.out.println(question);
        for (int i = 0; i < answerList.size(); i++) {
            System.out.println(i + 1 + ". " + answerList.get(i).getName());
        }
    }

    @Override
    public void save(final IQuestion question) {
        CsvQuestion csvQuestion = (CsvQuestion) question;
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        try {
            int select = in.nextInt();
            result.put(question, csvQuestion.answerList.get(select - 1));
        }catch (IllegalAccessError | IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
            save(question);
        }
    }

    @Override
    public void ask() {
        print();
        save(this);
    }

    @Override
    public Map<IQuestion<Boolean>, Answer<Boolean>> getResult() {
        return result;
    }

    @Override
    public String getName() {
        return question;
    }
}
