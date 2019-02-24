package ru.evgeny.question.impl;

import ru.evgeny.answer.Answer;
import ru.evgeny.answer.CsvAnswer;
import org.junit.Rule;

import org.junit.jupiter.api.Test;
import ru.evgeny.question.interfaces.IQuestion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvQuestionTest {

    private String ANSWER = "Test ru.evgeny.answer";
    private String QUESTION = "Test ru.evgeny.question";




    @Test
    void print() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        IQuestion<Boolean> question = new CsvQuestion(QUESTION, Arrays.asList(new CsvAnswer(ANSWER, false)));
        System.setOut(new PrintStream(outContent));
        question.print();
        String result = "Test ru.evgeny.question\n1. Test ru.evgeny.answer\n";
        assertEquals(result, outContent.toString());


    }

    @Test
    void save() throws NoSuchFieldException, IllegalAccessException {
        IQuestion<Boolean> question = new CsvQuestion(QUESTION, Arrays.asList(new CsvAnswer(ANSWER, false)));
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        question.save(question);
        Field field = question.getClass().getDeclaredField("result");
        field.setAccessible(true);
        Map<IQuestion<Boolean>, Answer<Boolean>> result = (Map<IQuestion<Boolean>, Answer<Boolean>>) field.get(question);
        assertEquals(result.get(question).getName(), ANSWER);
        assertEquals(result.get(question).getPoint(), false);

    }

}