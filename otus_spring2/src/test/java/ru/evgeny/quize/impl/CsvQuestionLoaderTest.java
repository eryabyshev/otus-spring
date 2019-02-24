package ru.evgeny.quize.impl;

import org.junit.jupiter.api.Test;
import ru.evgeny.quize.interfaces.IQuestionLoader;



class CsvQuestionLoaderTest {
    public static String PATH = "/Users/mac/Developer/otus_spring/otus_spring2/src/main/resources/test.csv";

    @Test
    public void csvLoadTest() {
        IQuestionLoader loader = new CsvQuestionLoader(PATH);
        loader.load();
    }

}