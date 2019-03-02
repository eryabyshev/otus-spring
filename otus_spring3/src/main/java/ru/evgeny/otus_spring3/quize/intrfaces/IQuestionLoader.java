package ru.evgeny.otus_spring3.quize.intrfaces;

import ru.evgeny.otus_spring3.question.interfaces.IQuestion;
import java.util.List;

public interface IQuestionLoader {
    List<IQuestion> load();
}
