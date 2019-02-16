package question.process;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionLoaderImpl implements IQuestionLoader {
    private static final String QUESTION = "question";
    private static final char[] PREFIX = {'a', 'b', 'c'};
    private String path;

    public QuestionLoaderImpl(String path) {
        this.path = path;
    }

    @Override
    public Map<String, List<String>> getQuestion() {

        Map<String, List<String>> result = new HashMap<>();
        CSVFormat csvFormat = CSVFormat.EXCEL.withHeader();
        try (CSVParser records = csvFormat.parse(new FileReader(path))) {
            for (CSVRecord record : records) {
                String key = record.get(QUESTION);
                List<String> variants = new ArrayList<>();
                for (int i = 0; i < PREFIX.length; i++) {
                    variants.add(record.get(QUESTION + " " + PREFIX[i]));
                }
                result.put(key, variants);
            }
            return result;
        } catch (IOException e) {
            return null;

        }
    }
}



