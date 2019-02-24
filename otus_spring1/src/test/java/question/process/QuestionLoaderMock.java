package ru.evgeny.question.process;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.*;

public class QuestionLoaderMock implements IQuestionLoader {
    @Override
    public Map<String, List<String>> getQuestion() {
        Map<String, List<String>> result = new HashMap<>();
        result.put("Откуда о нас узнали", asList(new String[]{"Друзья/Знакомые", "Интернет/Соц.сети", "Телевидение"}));
        result.put("Будите ли вы нас советовать?", asList(new String[]{"Да,конечно", "Может быть", "Точно, нет"}));
        result.put("Ваш вопрос был решен?", asList(new String[]{"Да, полностью", "Почти, но остались вопросы", "Нет, вопрос не был решен"}));
        result.put("Оцените оператора?", asList(new String[]{"Отличная компетенция", "Удовлетворительная компетенция", "Ужасный специалист"}));
        result.put("Оцените нас сервис", asList(new String[]{"Полезный сервис", "Может пригодиться", "Ужасный сервис"}));
        return result;
    }
}
