package ru.evgeny.quize.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.evgeny.answer.Answer;
import ru.evgeny.answer.CsvAnswer;
import ru.evgeny.question.impl.CsvQuestion;
import ru.evgeny.question.interfaces.IQuestion;
import ru.evgeny.quize.interfaces.IQuestionLoader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class CsvQuestionLoader implements IQuestionLoader {

    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private static final String POINT = "point";
    private static final char[] PREFIX = {'a', 'b', 'c'};
    private String path;

    @Autowired
    public CsvQuestionLoader(@Value("${csv.path}") String path) {
        this.path = path;
    }

    @Override
    public List<IQuestion> load() {
        List<IQuestion> questions = new ArrayList<>();
        CSVFormat csvFormat = CSVFormat.EXCEL.withHeader();
        try (CSVParser records = csvFormat.parse(new FileReader(path))) {
            for (CSVRecord record : records) {
                String question = record.get(QUESTION);
                List<Answer<Boolean>> answers = new ArrayList<>();
                for (int i = 0; i < PREFIX.length; i++) {
                    answers.add(new CsvAnswer(record.get(ANSWER + " " + PREFIX[i]), record.get(POINT + " " + PREFIX[i]).equals("1")));
                }
                questions.add(new CsvQuestion(question, answers));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
