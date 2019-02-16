package main.logic;

import question.process.IQuestionLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RealizeQuestionPool {
    private IQuestionLoader questionLoader;
    private Map<String, String> answer = new HashMap<>();
    private static Scanner in = new Scanner(System.in);
    private static final String INVALID_ANSWER = "Invalid answer!";

    public RealizeQuestionPool(final IQuestionLoader questionLoader) {
        this.questionLoader = questionLoader;
    }


    public RealizeQuestionPool startProcess() {
        Map<String, List<String>> question = questionLoader.getQuestion();
        question.forEach((key, value) ->{
            System.out.println(key);
            for (int i = 0; i < value.size(); i++) {
                System.out.println(i + 1 + " " + value.get(i));
            }
            System.out.print("> ");
            try {
                int i = in.nextInt();
                answer.put(key, value.get(i - 1));

            }catch (Exception e) {
                System.out.println(INVALID_ANSWER);
            }
        });
        return this;
    }

    public void printAnswer() {
        answer.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
