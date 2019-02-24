package ru.evgeny.quize.interfaces;

import ru.evgeny.question.interfaces.IQuestion;

import java.util.List;

public interface IQuestionLoader {

    List<IQuestion> load();

}
