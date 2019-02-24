package ru.evgeny.question.process;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionLoaderImplTest {

    @org.junit.jupiter.api.Test
    void getQuestionTest() {
        IQuestionLoader loader = new QuestionLoaderImpl("/Users/mac/Developer/otus-java/otus_spring/src/main/resources/ru.evgeny.question.csv");
        Map<String, List<String>> question = loader.getQuestion();
        assertNotNull(question);
        IQuestionLoader mock = new QuestionLoaderMock();
        assertArrayEquals(mock.getQuestion().entrySet().toArray(), loader.getQuestion().entrySet().toArray());
    }
}